package com.rcuebillas.cashregistry.service;

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
        logger.info("Executing Put Cash");
        if (input.length < 6) {
            return "input is less than required number of parameters";
        }

        if (input.length > 6) {
            return "input is more than required number of parameters";
        }
        List<Cash> cashes = CashUtil.generateCashes(input);
        cashRepository.saveAll(cashes);
        return showCashServiceImpl.execute(input);
    }
}
