package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.repository.CashRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PutCashServiceIT {

    @Autowired
    private CashRegistryService putCashServiceImpl;

    @Autowired
    private CashRepository cashRepository;

    @AfterEach
    void tearDown() {
        cashRepository.deleteAll();
    }

    @Test
    void testInputMoreThan6() {
        String[] input = "put 1 2 3 4 5 6".split(" ");

        Assertions.assertThat(putCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputLessThan6() {
        String[] input = "put 1 2 3 4".split(" ");

        Assertions.assertThat(putCashServiceImpl.execute(input))
                .isEqualTo(Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS);
    }

    @Test
    void testInputNotANumber() {
        String[] input = "put 1 2 3 4 f".split(" ");
        String expected = "$0 0 0 0 0 0";

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
    }

    @Test
    void testInputEquals6() {
        String[] input = "put 1 2 3 4 5".split(" ");
        String expected = "$68 1 2 3 4 5";

        Assertions.assertThat(putCashServiceImpl.execute(input)).isEqualTo(expected);
    }
}
