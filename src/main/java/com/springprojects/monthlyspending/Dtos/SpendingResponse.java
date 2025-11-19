package com.springprojects.monthlyspending.Dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SpendingResponse {
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;
    private Date date;
    private BigDecimal totalSum;
}
