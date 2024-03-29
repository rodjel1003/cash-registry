package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestUnknownCashService {

    private CashRegistryService unknownCashServiceImpl;

    @BeforeEach
    void setUp() {
        unknownCashServiceImpl = new UnknownCashServiceImpl();
    }

    @Test
    void testUnknown() {
        String[] input = "abcdef".split(" ");

        Assertions.assertThat(unknownCashServiceImpl.execute(input)).isEqualTo(Constants.UNKNOWN_COMMAND);
    }
}
