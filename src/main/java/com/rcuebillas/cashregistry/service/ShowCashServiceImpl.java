package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.repository.CashRepository;
import com.rcuebillas.cashregistry.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShowCashServiceImpl implements CashRegistryService {

    private static final Logger logger = LoggerFactory.getLogger(ShowCashServiceImpl.class);

    private final CashRepository cashRepository;

    public ShowCashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public String execute(String[] input) {
        logger.info(Constants.EXECUTING_SHOW_CASH);
        StringBuilder sb = new StringBuilder();
        int totalValue = 0;

        for (Bill bill: Bill.values()) {
            int count = cashRepository.findByValue(bill.getValue()).size();
            sb.append(count).append(" ");
            totalValue += bill.getValue() * count;
        }

        return String.format(Constants.S_S_S, Constants.DOLLAR, totalValue, sb.toString().trim());
    }
}
