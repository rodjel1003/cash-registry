package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnknownCashServiceIT {

    @Autowired
    private CashRegistryService unknownCashServiceImpl;

    @Test
    void testUnknown() {
        String[] input = "abcdef".split(" ");

        Assertions.assertThat(unknownCashServiceImpl.execute(input)).isEqualTo(Constants.UNKNOWN_COMMAND);
    }
}
