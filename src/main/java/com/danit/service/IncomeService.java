package com.danit.service;

import com.danit.model.Income;
import com.danit.repository.IncomeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public void sendIncomes() {
        createRandomIncomes().forEach(incomeRepository::save);
    }

    private List<Income> createRandomIncomes() {
        return IntStream.range(0, 10000)
                .mapToObj(i -> Income.builder()
                        .amount(new BigDecimal(ThreadLocalRandom.current().nextInt(100, 10000)))
                        .developerId(ThreadLocalRandom.current().nextInt(1, 3))
                        .build())
                .collect(Collectors.toList());
    }

}
