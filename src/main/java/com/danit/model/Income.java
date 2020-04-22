package com.danit.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Income {
    private int developerId;
    private BigDecimal amount;
}
