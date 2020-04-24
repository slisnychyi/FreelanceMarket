package com.danit.controller;

import com.danit.service.IncomeService;


public class IncomeController {

    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    public void sendIncomes( int count) {
        incomeService.sendIncomes(count);
    }

}
