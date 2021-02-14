package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CashRegistryFactoryImpl implements CashRegistryFactory {

    private static final Logger logger = LoggerFactory.getLogger(CashRegistryFactoryImpl.class);

    private final CashRegistryService changeCashServiceImpl;
    private final CashRegistryService putCashServiceImpl;
    private final CashRegistryService showCashServiceImpl;
    private final CashRegistryService takeCashServiceImpl;
    private final CashRegistryService unknownCashServiceImpl;

    public CashRegistryFactoryImpl(CashRegistryService changeCashServiceImpl, CashRegistryService putCashServiceImpl,
                                   CashRegistryService showCashServiceImpl, CashRegistryService takeCashServiceImpl,
                                   CashRegistryService unknownCashServiceImpl) {
        this.changeCashServiceImpl = changeCashServiceImpl;
        this.putCashServiceImpl = putCashServiceImpl;
        this.showCashServiceImpl = showCashServiceImpl;
        this.takeCashServiceImpl = takeCashServiceImpl;
        this.unknownCashServiceImpl = unknownCashServiceImpl;
    }

    @Override
    public CashRegistryService getCashRegistry(Command command) {
        logger.info(Constants.GETTING_CASH_REGISTRY_SERVICE_WITH_COMMAND, command);
        switch (command) {
            case SHOW:
                return showCashServiceImpl;
            case PUT:
                return putCashServiceImpl;
            case TAKE:
                return takeCashServiceImpl;
            case CHANGE:
                return changeCashServiceImpl;
            default:
                return unknownCashServiceImpl;
        }
    }
}
