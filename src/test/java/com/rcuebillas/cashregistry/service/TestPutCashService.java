package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.repository.CashRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestPutCashService {

    private CashRegistryService putCashServiceImpl;

    @Mock
    private CashRepository cashRepository;

    @Mock
    private CashRegistryService showCashServiceImpl;

    @BeforeEach
    void setUp() {
        putCashServiceImpl = new PutCashServiceImpl(cashRepository, showCashServiceImpl);
    }

    @Test
    void testInputMoreThan6() {
        String[] input = "put 1 2 3 4 5 6".split(" ");
        String expected = "input is more than required number of parameters";

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository, never()).saveAll(anyList());
        verify(showCashServiceImpl, never()).execute(input);
    }

    @Test
    void testInputLessThan6() {
        String[] input = "put 1 2 3 4".split(" ");
        String expected = "input is less than required number of parameters";

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository, never()).saveAll(anyList());
        verify(showCashServiceImpl, never()).execute(input);
    }

    @Test
    void testInputNotANumber() {
        String[] input = "put 1 2 3 4 f".split(" ");
        String expected = "$0 0 0 0 0 0";

        doReturn(expected).when(showCashServiceImpl).execute(input);

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository).saveAll(anyList());
        verify(showCashServiceImpl).execute(input);
    }

    @Test
    void testInputEquals6() {
        String[] input = "put 1 2 3 4 5".split(" ");
        String expected = "$68 1 2 3 4 5";

        doReturn(expected).when(showCashServiceImpl).execute(input);

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository).saveAll(anyList());
        verify(showCashServiceImpl).execute(input);
    }
}
