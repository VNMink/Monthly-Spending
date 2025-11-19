package com.springprojects.monthlyspending.Controllers;

import com.springprojects.monthlyspending.Dtos.DateRange;
import com.springprojects.monthlyspending.Dtos.SpendingListResponse;
import com.springprojects.monthlyspending.Dtos.SpendingRequest;
import com.springprojects.monthlyspending.Dtos.SpendingResponse;
import com.springprojects.monthlyspending.Services.spending.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/spending")
public class SpendingController {

    private final SpendingService service;

    @Autowired
    public SpendingController(SpendingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SpendingResponse> createSpending(
            @RequestBody SpendingRequest request) {
        return  ResponseEntity.ok(service.createSpending(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpendingResponse> updateSpending(
            @RequestBody SpendingRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.updateSpending(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpending(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.deleteSpending(id));
    }

    @PostMapping("/search/{name}")
    public ResponseEntity<List<SpendingResponse>> searchSpending(
            @PathVariable String name) {
        return ResponseEntity.ok(service.getSpendingByName(name));
    }

    @PostMapping("/search/date")
    public ResponseEntity<SpendingListResponse> searchSpending(
            @RequestBody DateRange dateRange) {

        SpendingListResponse response = new SpendingListResponse();
        List<SpendingResponse> foundSpending = service.getSpendingByDate(dateRange.getStart(), dateRange.getEnd());
        BigDecimal totalSpending = foundSpending.stream()
                        .map(SpendingResponse::getTotalSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setSpendingList(foundSpending);
        response.setTotalSpending(totalSpending);


        return ResponseEntity.ok(response);
    }
}
