package com.rcuebillas.cashregistry.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShowCashServiceIT {

    @Autowired
    private CashRegistryService showCashServiceImpl;

    @Test
    void testShow() {
        String[] input = "show".split(" ");
        String expectedOutput = "$0 0 0 0 0 0";

        Assertions.assertThat(showCashServiceImpl.execute(input)).isEqualTo(expectedOutput);
    }
}
