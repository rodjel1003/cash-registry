package com.rcuebillas.cashregistry.util;

import com.rcuebillas.cashregistry.model.Bill;
import com.rcuebillas.cashregistry.model.Cash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CashUtil {

    private static final Logger logger = LoggerFactory.getLogger(CashUtil.class);

    private CashUtil() {
    }

    public static List<Cash> generateCashes(String[] inputArray) {
        List<Cash> cashes = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            try {
                int count = Integer.parseInt(inputArray[i]);
                for (int j = 0; j < count; j++) {
                    cashes.add(new Cash(Bill.values()[i-1].getValue()));
                }
            } catch (NumberFormatException e) {
                logger.debug("{} is not a valid number", inputArray[i]);
                System.out.printf("%s is not a valid number", inputArray[i]);
                System.out.println();
                return Collections.emptyList();
            }
        }

        return cashes;

    }
}
