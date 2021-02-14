package com.rcuebillas.cashregistry.controller;

import com.rcuebillas.cashregistry.contant.Constants;
import com.rcuebillas.cashregistry.model.Command;
import com.rcuebillas.cashregistry.service.CashRegistryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class CashRegistryController {

    private static final Logger logger = LoggerFactory.getLogger(CashRegistryController.class);

    private final CashRegistryFactory cashRegistryFactory;

    private final boolean testing;

    public CashRegistryController(CashRegistryFactory cashRegistryFactory, @Value("${testing:false}") boolean testing) {
        this.cashRegistryFactory = cashRegistryFactory;
        this.testing = testing;
    }

    public void run() {
        while(!testing) {
            logger.info(Constants.WAITING_FOR_A_COMMAND);
            System.out.println(Constants.WAITING_FOR_A_COMMAND);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            logger.info("input: {}", input);
            String[] inputArray = input.split(" ");
            Command command = Command.getByName(inputArray[0]);

            if(Command.QUIT == command) {
                logger.info(Constants.QUITTING_THE_PROGRAM);
                System.out.println(Constants.QUITTING_THE_PROGRAM);
                break;
            }


            try {
                String output = cashRegistryFactory.getCashRegistry(command).execute(inputArray);
                logger.info(output);
                System.out.println(output);
            } catch (Exception e) {
                logger.error(e.getMessage());
                System.out.println(Constants.UNKNOWN_ERROR_OCCURRED);
            }

        }

    }
}
