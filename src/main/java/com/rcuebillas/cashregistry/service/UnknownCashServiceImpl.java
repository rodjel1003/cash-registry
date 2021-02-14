package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.rcuebillas.cashregistry.contant.Constants.UNKNOWN_COMMAND;

@Service
public class UnknownCashServiceImpl implements CashRegistryService {

    private static final Logger logger = LoggerFactory.getLogger(UnknownCashServiceImpl.class);


    @Override
    public String execute(String[] input) {
        logger.info(Constants.EXECUTING_UNKNOWN_COMMAND);
        return Constants.UNKNOWN_COMMAND;
    }
}
