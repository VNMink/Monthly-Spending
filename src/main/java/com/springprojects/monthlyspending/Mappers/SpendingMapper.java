package com.springprojects.monthlyspending.Mappers;

import com.springprojects.monthlyspending.Dtos.SpendingRequest;
import com.springprojects.monthlyspending.Dtos.SpendingResponse;
import com.springprojects.monthlyspending.Entities.Spending;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpendingMapper {

    Spending requestToEntity(SpendingRequest request);

    SpendingResponse entityToResponse(Spending spending);

    List<SpendingResponse> entityListToResponseList(List<Spending> foundSpendings);
}
