package com.springprojects.monthlyspending.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "spendings")
public class Spending {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private BigDecimal itemPrice;
    @Column(nullable = false)
    private int itemQuantity;
    @Column(nullable = false)
    private Date date = new Date();
    @Column(nullable = false)
    private BigDecimal totalSum;
}
