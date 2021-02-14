package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Cash;
import com.rcuebillas.cashregistry.repository.CashRepository;
import com.rcuebillas.cashregistry.util.CashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeCashServiceImpl implements CashRegistryService {

    private static final Logger logger = LoggerFactory.getLogger(TakeCashServiceImpl.class);

    private final CashRepository cashRepository;
    private final CashRegistryService showCashServiceImpl;

    public TakeCashServiceImpl(CashRepository cashRepository, CashRegistryService showCashServiceImpl) {
        this.cashRepository = cashRepository;
        this.showCashServiceImpl = showCashServiceImpl;
    }

    @Override
    public String execute(String[] input) {
        logger.info(Constants.EXECUTING_TAKE_CASH);
        if (input.length < 6) {
            return Constants.INPUT_IS_LESS_THAN_REQUIRED_NUMBER_OF_PARAMETERS;
        }

        if (input.length > 6) {
            return Constants.INPUT_IS_MORE_THAN_REQUIRED_NUMBER_OF_PARAMETERS;
        }
        List<Cash> cashes = CashUtil.generateCashes(input);
        for(Cash cash : cashes) {
            List<Cash> cashesFromRepo = cashRepository.findByValue(cash.getValue());
            if(!cashesFromRepo.isEmpty()) {
                cashRepository.delete(cashesFromRepo.get(0));
                logger.info(Constants.DISPENSING_BILL, cash.getValue());
                System.out.printf(Constants.DISPENSING_S_BILL, cash.getValue());
            } else {
                logger.info(Constants.SORRY_NO_MORE_BILL_IN_THIS_MACHINE, cash.getValue());
                System.out.printf(Constants.SORRY_NO_MORE_S_BILL_IN_THIS_MACHINE, cash.getValue());
            }
            System.out.println();
        }

        return showCashServiceImpl.execute(input);
    }
}
