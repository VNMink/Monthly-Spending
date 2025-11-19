package com.springprojects.monthlyspending.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class DateRange {
    private Date start;
    private Date end;
}
