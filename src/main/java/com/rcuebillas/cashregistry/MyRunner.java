package com.rcuebillas.cashregistry;

import com.rcuebillas.cashregistry.service.CashRegistryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final CashRegistryFactory cashRegistryFactory;

    public MyRunner(CashRegistryFactory cashRegistryFactory) {
        this.cashRegistryFactory = cashRegistryFactory;
    }

    @Override
    public void run(String... args) {
        logger.info("Start program");
        System.out.println("Start program");

        while(true) {
            logger.info("Waiting for a command");
            System.out.println("Waiting for a command");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            logger.info("input: {}", input);
            String[] inputArray = input.split(" ");
            String command = inputArray[0];

            if("QUIT".equalsIgnoreCase(command)) {
                logger.info("Quitting the program");
                System.out.println("Quitting the program");
                break;
            }


            try {
                String output = cashRegistryFactory.getCashRegistry(command).execute(inputArray);
                logger.info(output);
                System.out.println(output);
            } catch (Exception e) {
                logger.error(e.getMessage());
                System.out.println("Unknown Error occurred");
            }

        }

    }
}
