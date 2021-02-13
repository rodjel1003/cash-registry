package com.rcuebillas.cashregistry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UnknownCashServiceImpl implements CashRegistryService {

    private static final Logger logger = LoggerFactory.getLogger(UnknownCashServiceImpl.class);

    @Override
    public String execute(String[] input) {
        logger.info("Executing Unknown Command");
        return "Unknown command";
    }
}
