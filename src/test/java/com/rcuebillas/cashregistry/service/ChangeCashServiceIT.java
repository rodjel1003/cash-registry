package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Cash;
import com.rcuebillas.cashregistry.repository.CashRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChangeCashServiceIT {

    @Autowired
    private CashRegistryService changeCashServiceImpl;

    @Autowired
    private CashRepository cashRepository;

    @BeforeEach
    void setUp() {
        cashRepository.saveAll(Arrays.asList(new Cash(20), new Cash(10), new Cash(5), new Cash(2),
                new Cash(1), new Cash(20), new Cash(10), new Cash(5), new Cash(2),
                new Cash(1)));
    }

    @AfterEach
    void tearDown() {
        cashRepository.deleteAll();
    }

    @Test
    void testInputMoreThanTwo() {
        String[] input = "change 1 2".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputLessThanTwo() {
        String[] input = "change".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputNotANumber() {
        String[] input = "change f".split(" ");
        String expected = String.format(Constants.S_NOT_A_VALID_NUMBER, "f");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(expected);
    }

    @Test
    void testAmountIsZero() {
        String[] input = "change 0".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.AMOUNT_OF_CHANGE_IS_0);
    }

    @Test
    void testNotEnoughBills() {
        String[] input = "change 80".split(" ");

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(Constants.NOT_ENOUGH_BILLS);
    }

    @Test
    void testInputEqualsTwoWithEnoughBills() {
        String[] input = "change 1".split(" ");
        String expected = "0 0 0 0 1";

        Assertions.assertThat(changeCashServiceImpl.execute(input)).isEqualTo(expected);
    }
}
