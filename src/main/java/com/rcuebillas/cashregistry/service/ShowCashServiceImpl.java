package com.rcuebillas.cashregistry.service;

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
        logger.info("Executing Show Cash");
        StringBuilder sb = new StringBuilder();
        int totalValue = 0;

        for (Bill bill: Bill.values()) {
            int count = cashRepository.findByValue(bill.getValue()).size();
            sb.append(count).append(" ");
            totalValue += bill.getValue() * count;
        }

        return String.format("%s%s %s", "$", totalValue, sb.toString().trim());
    }
}
