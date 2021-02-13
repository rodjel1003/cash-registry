package com.rcuebillas.cashregistry.service;

public interface CashRegistryFactory {
    CashRegistryService getCashRegistry(String command);
}
