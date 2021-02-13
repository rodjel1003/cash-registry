package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.model.Bill;
import com.rcuebillas.cashregistry.model.Cash;
import com.rcuebillas.cashregistry.repository.CashRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ChangeCashServiceImpl implements CashRegistryService{

    private static final Logger logger = LoggerFactory.getLogger(ChangeCashServiceImpl.class);

    private final CashRepository cashRepository;

    public ChangeCashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public String execute(String[] input) {
        logger.info("Executing Change Cash");
        int amount;
        if(input.length < 2) {
            return "input is less than required number of parameters";
        }
        if(input.length > 2) {
            return "input is more than required number of parameters";
        }

        try {
            amount = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            return String.format("%s is not a valid number", input[1]);
        }

        if(amount == 0) {
            return "Amount of change is 0, no need to dispense bills";
        }

        List<Cash> cashes = new ArrayList<>();
        List<Cash> cashesFromRepo = new ArrayList<>();
        for (Cash cash : cashRepository.findAll()) {
            cashesFromRepo.add(cash);
        }

        List<Cash> toRemoveCashes = changeUtil(cashes, cashesFromRepo, amount);
        if(toRemoveCashes.isEmpty()) {
            return "Sorry, not enough bills for your change";
        }

        cashRepository.deleteAll(toRemoveCashes);
        return displayRemovedCash(toRemoveCashes);
    }

    private String displayRemovedCash(List<Cash> toRemoveCashes) {
        int[] tally = new int[5];
        for (Cash cash : toRemoveCashes) {
            int index = Bill.getIndexByValue(cash.getValue());
            if (index != -1) {
                tally[index]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : tally) {
            sb.append(i).append(" ");
        }
        return sb.toString().trim();
    }


    private List<Cash> changeUtil(List<Cash> cashes, List<Cash> cashesFromRepo, int amount) {
        if(amount == 0) return cashes;

        for (int i = 0; i < cashesFromRepo.size(); i++) {
            Cash cashRep = cashesFromRepo.get(i);
            if(amount >= cashRep.getValue()) {
                cashes.add(cashRep);
                cashesFromRepo.remove(cashRep);
                List<Cash> result = changeUtil(cashes, cashesFromRepo, amount - cashRep.getValue());
                if(!result.isEmpty()) {
                    return result;
                }
                cashes.remove(cashRep);
                cashesFromRepo.add(cashRep);
            }
        }

        return Collections.emptyList();
    }
}
