package com.springprojects.monthlyspending.Services.spending;

import com.springprojects.monthlyspending.Dtos.SpendingRequest;
import com.springprojects.monthlyspending.Dtos.SpendingResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface ISpendingService {
    SpendingResponse createSpending(SpendingRequest request) throws IOException;

    SpendingResponse updateSpending(SpendingRequest request, Long id) throws IOException;

    String deleteSpending(Long id);

    List<SpendingResponse> getSpendingByName(String name);

    List<SpendingResponse> getSpendingByDate(Date startDate, Date endDate);
}
