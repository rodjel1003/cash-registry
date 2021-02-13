package com.rcuebillas.cashregistry.service;

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
        logger.info("Executing Take Cash");
        if (input.length < 6) {
            return "input is less than required number of parameters";
        }

        if (input.length > 6) {
            return "input is more than required number of parameters";
        }
        List<Cash> cashes = CashUtil.generateCashes(input);
        for(Cash cash : cashes) {
            List<Cash> cashesFromRepo = cashRepository.findByValue(cash.getValue());
            if(!cashesFromRepo.isEmpty()) {
                cashRepository.delete(cashesFromRepo.get(0));
                logger.info("Dispensing {} bill", cash.getValue());
                System.out.printf("Dispensing %s bill", cash.getValue());
            } else {
                logger.info("Sorry, no more {} bill in this machine", cash.getValue());
                System.out.printf("Sorry, no more %s bill in this machine", cash.getValue());
            }
            System.out.println();
        }

        return showCashServiceImpl.execute(input);
    }
}
