package com.rcuebillas.cashregistry.service;

import com.rcuebillas.cashregistry.model.Command;

public interface CashRegistryFactory {
    CashRegistryService getCashRegistry(Command command);
}
