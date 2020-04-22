package com.danit.controller;

import com.danit.repository.IncomeRepository;
import com.danit.service.IncomeService;

import java.sql.SQLException;


public class IncomeController {

    private IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    public void sendIncomes() {
        incomeService.sendIncomes();
    }

    public static void main(String[] args) throws SQLException {
        IncomeController incomeController = new IncomeController(new IncomeService(new IncomeRepository()));
        incomeController.sendIncomes();
    }

}
