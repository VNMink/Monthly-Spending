package com.springprojects.monthlyspending.Dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpendingListResponse {
    private List<SpendingResponse> spendingList;
    private BigDecimal totalSpending;
}
