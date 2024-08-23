package com.luobd.server.cash.core.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CashProjectTotalAmount {


    private Long projectId;


    private BigDecimal totalAmount;

}
