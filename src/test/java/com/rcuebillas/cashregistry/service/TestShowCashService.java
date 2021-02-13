package com.rcuebillas.cashregistry.service;

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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TestShowCashService {

    private CashRegistryService showCashServiceImpl;

    @Mock
    private CashRepository cashRepository;

    @BeforeEach
    void setUp() {
        showCashServiceImpl = new ShowCashServiceImpl(cashRepository);
    }

    @Test
    void testShow() {
        String[] input = "show".split(" ");
        String expectedOutput = "$0 0 0 0 0 0";

        doReturn(Collections.emptyList()).when(cashRepository).findByValue(anyInt());

        Assertions.assertThat(showCashServiceImpl.execute(input)).isEqualTo(expectedOutput);
    }

    @Test
    void testShowCorrectOrderAndAmount() {
        String[] input = "show".split(" ");
        String expectedOutput = "$68 1 2 3 4 5";

        doReturn(Collections.singletonList(new Cash())).when(cashRepository).findByValue(20);
        doReturn(Arrays.asList(new Cash(), new Cash())).when(cashRepository).findByValue(10);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash())).when(cashRepository).findByValue(5);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash(), new Cash())).when(cashRepository).findByValue(2);
        doReturn(Arrays.asList(new Cash(), new Cash(), new Cash(), new Cash(), new Cash())).when(cashRepository)
                .findByValue(1);

        Assertions.assertThat(showCashServiceImpl.execute(input)).isEqualTo(expectedOutput);
    }
}
