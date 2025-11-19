package com.springprojects.monthlyspending.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private Integer status;
    private String message;
    private Object data;
}
