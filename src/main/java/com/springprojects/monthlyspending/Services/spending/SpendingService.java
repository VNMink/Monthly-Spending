package com.springprojects.monthlyspending.Services.spending;

import com.springprojects.monthlyspending.Dtos.SpendingRequest;
import com.springprojects.monthlyspending.Dtos.SpendingResponse;
import com.springprojects.monthlyspending.Entities.Spending;
import com.springprojects.monthlyspending.Mappers.SpendingMapper;
import com.springprojects.monthlyspending.Repositories.SpendingRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class SpendingService implements ISpendingService{

    private final SpendingMapper spendingMapper;
    private final SpendingRepository spendingRepository;

    public SpendingService(SpendingMapper spendingMapper,
                           SpendingRepository spendingRepository) {
        this.spendingMapper =  spendingMapper;
        this.spendingRepository = spendingRepository;
    }

    @Override
    public SpendingResponse createSpending(SpendingRequest request){
        Spending spending = spendingMapper.requestToEntity(request);
        BigDecimal totalSum = request.getItemPrice().multiply(BigDecimal.valueOf(spending.getItemQuantity()));
        spending.setTotalSum(totalSum);

        return spendingMapper.entityToResponse(spendingRepository.save(spending));
    }

    @Override
    public SpendingResponse updateSpending(SpendingRequest request, Long id){
        Optional<Spending> alreadyExists = spendingRepository.findById(id);
        if(alreadyExists.isPresent()){
            Spending updatedSpending = spendingMapper.requestToEntity(request);
            updatedSpending.setDate(new Date());
            updatedSpending.setId(id);
            return spendingMapper.entityToResponse(spendingRepository.save(updatedSpending));
        } else {
            throw new RuntimeException("Spending not found");
        }
    }

    @Override
    public String deleteSpending(Long id){
        Optional<Spending> alreadyExists = spendingRepository.findById(id);
        if(alreadyExists.isPresent()){
            spendingRepository.deleteById(id);
            return "Success";
        }  else {
            throw new RuntimeException("Spending not found");
        }
    }

    @Override
    public List<SpendingResponse> getSpendingByName(String name){
        List<Spending> foundSpendings = spendingRepository.findAllByItemNameContainingIgnoreCase(name);

        return spendingMapper.entityListToResponseList(foundSpendings);
    }

    @Override
    public List<SpendingResponse> getSpendingByDate(Date start, Date end){
        List<Spending> foundSpendings = spendingRepository.findAllByDateBetween(start, end);

        return spendingMapper.entityListToResponseList(foundSpendings);
    }

}
