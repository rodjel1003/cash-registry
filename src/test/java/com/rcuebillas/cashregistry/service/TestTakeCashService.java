package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Cash;
import com.rcuebillas.cashregistry.repository.CashRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestTakeCashService {

    private CashRegistryService takeCashServiceImpl;

    @Mock
    private CashRepository cashRepository;

    @Mock
    private CashRegistryService showCashServiceImpl;

    @BeforeEach
    void setUp() {
        takeCashServiceImpl = new TakeCashServiceImpl(cashRepository, showCashServiceImpl);
    }

    @Test
    void testInputMoreThan6() {
        String[] input = "take 1 2 3 4 5 6".split(" ");

        Assertions.assertThat(takeCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
        verify(cashRepository, never()).saveAll(anyList());
        verify(showCashServiceImpl, never()).execute(input);
    }

    @Test
    void testInputLessThan6() {
        String[] input = "take 1 2 3 4".split(" ");

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
        verify(cashRepository, never()).saveAll(anyList());
        verify(showCashServiceImpl, never()).execute(input);
    }

    @Test
    void testInputNotANumber() {
        String[] input = "take 1 2 3 4 f".split(" ");
        String expected = "$68 1 2 3 4 5";

        doReturn(expected).when(showCashServiceImpl).execute(input);

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository, never()).findByValue(20);
        verify(cashRepository, never()).findByValue(10);
        verify(cashRepository, never()).findByValue(2);
        verify(cashRepository, never()).findByValue(5);
        verify(cashRepository, never()).findByValue(1);
        verify(cashRepository, never()).delete(any());
        verify(showCashServiceImpl).execute(input);
    }

    @Test
    void testInputEquals6() {
        String[] input = "take 1 2 3 4 5".split(" ");
        String expected = "$68 1 2 3 4 5";

        doReturn(expected).when(showCashServiceImpl).execute(input);
        doReturn(Collections.singletonList(new Cash())).when(cashRepository).findByValue(20);
        doReturn(Arrays.asList(new Cash(), new Cash())).when(cashRepository).findByValue(10);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash())).when(cashRepository).findByValue(5);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash(), new Cash())).when(cashRepository).findByValue(2);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash(), new Cash(), new Cash())).when(cashRepository)
                .findByValue(1);

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository).findByValue(20);
        verify(cashRepository, times(2)).findByValue(10);
        verify(cashRepository, times(3)).findByValue(5);
        verify(cashRepository, times(4)).findByValue(2);
        verify(cashRepository, times(5)).findByValue(1);
        verify(cashRepository, times(15)).delete(any());
        verify(showCashServiceImpl).execute(input);
    }
}
