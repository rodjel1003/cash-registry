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
public class TakeCashServiceIT {

    @Autowired
    private CashRegistryService takeCashServiceImpl;

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
    void testInputMoreThan6() {
        String[] input = "take 1 2 3 4 5 6".split(" ");

        Assertions.assertThat(takeCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputLessThan6() {
        String[] input = "take 1 2 3 4".split(" ");

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputNotANumber() {
        String[] input = "take 1 2 3 4 f".split(" ");
        String expected = "$76 2 2 2 2 2";

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(expected);
    }

    @Test
    void testInputEquals6() {

        String[] input = "take 2 2 2 2 2".split(" ");
        String expected = "$0 0 0 0 0 0";

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(expected);
    }

    @Test
    void testTakeWithRemaining() {
        String[] input = "take 1 1 1 1 1".split(" ");
        String expected = "$38 1 1 1 1 1";

        Assertions.assertThat(takeCashServiceImpl.execute(input)).isEqualTo(expected);
    }
}
