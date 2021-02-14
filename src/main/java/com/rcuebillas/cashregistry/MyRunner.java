package com.rcuebillas.cashregistry;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.controller.CashRegistryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final CashRegistryController cashRegistryController;

    public MyRunner(CashRegistryController cashRegistryController) {
        this.cashRegistryController = cashRegistryController;
    }

    @Override
    public void run(String... args) {
        logger.info(Constants.START_PROGRAM);
        System.out.println(Constants.START_PROGRAM);

        cashRegistryController.run();

        logger.info(Constants.END_PROGRAM);
        System.out.println(Constants.END_PROGRAM);
    }
}
