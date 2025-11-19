package com.springprojects.monthlyspending.Dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpendingRequest {
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;
}
