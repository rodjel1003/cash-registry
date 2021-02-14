package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Cash;
import com.rcuebillas.cashregistry.util.CashUtil;
import com.rcuebillas.cashregistry.repository.CashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PutCashServiceImpl implements CashRegistryService {

    private static final Logger logger = LoggerFactory.getLogger(PutCashServiceImpl.class);

    private final CashRepository cashRepository;
    private final CashRegistryService showCashServiceImpl;

    public PutCashServiceImpl(CashRepository cashRepository, CashRegistryService showCashServiceImpl) {
        this.cashRepository = cashRepository;
        this.showCashServiceImpl = showCashServiceImpl;
    }

    @Override
    public String execute(String[] input) {
        logger.info(Constants.EXECUTING_PUT_CASH);
        if (input.length < 6) {
            return Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS;
        }

        if (input.length > 6) {
            return Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS;
        }
        List<Cash> cashes = CashUtil.generateCashes(input);
        cashRepository.saveAll(cashes);
        return showCashServiceImpl.execute(input);
    }
}
