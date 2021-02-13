package com.rcuebillas.cashregistry.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestCashRegistryFactory {

    private CashRegistryFactory cashRegistryFactoryImpl;

    @Mock
    private CashRegistryService changeCashServiceImpl;

    @Mock
    private CashRegistryService putCashServiceImpl;

    @Mock
    private CashRegistryService showCashServiceImpl;

    @Mock
    private CashRegistryService takeCashServiceImpl;

    @Mock
    private CashRegistryService unknownCashServiceImpl;

    @BeforeEach
    void setUp() {
        cashRegistryFactoryImpl = new CashRegistryFactoryImpl(changeCashServiceImpl, putCashServiceImpl,
                showCashServiceImpl, takeCashServiceImpl, unknownCashServiceImpl);
    }

    @Test
    void testShowService() {
        String command = "show";
        Assertions.assertThat(cashRegistryFactoryImpl.getCashRegistry(command)).isEqualTo(showCashServiceImpl);
    }

    @Test
    void testChangeService() {
        String command = "change";
        Assertions.assertThat(cashRegistryFactoryImpl.getCashRegistry(command)).isEqualTo(changeCashServiceImpl);
    }

    @Test
    void testPutService() {
        String command = "put";
        Assertions.assertThat(cashRegistryFactoryImpl.getCashRegistry(command)).isEqualTo(putCashServiceImpl);
    }

    @Test
    void testTakeService() {
        String command = "take";
        Assertions.assertThat(cashRegistryFactoryImpl.getCashRegistry(command)).isEqualTo(takeCashServiceImpl);
    }

    @Test
    void testUnknownService() {
        String command = "abcdefg";
        Assertions.assertThat(cashRegistryFactoryImpl.getCashRegistry(command)).isEqualTo(unknownCashServiceImpl);
    }
}
