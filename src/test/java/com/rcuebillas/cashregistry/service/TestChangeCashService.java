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
class TestChangeCashService {

    private CashRegistryService changeCashServiceImpl;

    @Mock
    private CashRepository cashRepository;

    @BeforeEach
    void setUp() {
        changeCashServiceImpl = new ChangeCashServiceImpl(cashRepository);
    }

    @Test
    void testInputMoreThanTwo() {
        String[] input = "change 1 2".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
        verify(cashRepository, never()).findAll();
        verify(cashRepository, never()).deleteAll(anyList());
    }

    @Test
    void testInputLessThanTwo() {
        String[] input = "change".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
        verify(cashRepository, never()).findAll();
        verify(cashRepository, never()).deleteAll(anyList());
    }

    @Test
    void testInputNotANumber() {
        String[] input = "change f".split(" ");
        String expected = String.format(Constants.S_NOT_A_VALID_NUMBER, "f");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository, never()).findAll();
        verify(cashRepository, never()).deleteAll(anyList());
    }

    @Test
    void testAmountIsZero() {
        String[] input = "change 0".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.AMOUNT_OF_CHANGE_IS_0);
        verify(cashRepository, never()).findAll();
        verify(cashRepository, never()).deleteAll(anyList());
    }

    @Test
    void testNotEnoughBills() {
        String[] input = "change 2".split(" ");

        doReturn(Collections.singletonList(new Cash(1))).when(cashRepository).findAll();

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.NOT_ENOUGH_BILLS);
        verify(cashRepository).findAll();
        verify(cashRepository, never()).deleteAll(anyList());
    }

    @Test
    void testInputEqualsTwoWithEnoughBills() {
        String[] input = "change 2".split(" ");
        String expected = "0 0 0 0 2";

        doReturn(Arrays.asList(new Cash(1), new Cash(1))).when(cashRepository).findAll();

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(expected);
        verify(cashRepository).findAll();
        verify(cashRepository).deleteAll(anyList());
    }
}
