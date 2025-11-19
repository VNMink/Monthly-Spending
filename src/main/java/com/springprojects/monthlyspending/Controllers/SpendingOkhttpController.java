package com.springprojects.monthlyspending.Controllers;

import com.springprojects.monthlyspending.Dtos.SpendingRequest;
import com.springprojects.monthlyspending.Services.okhttp.SpendingOkhttpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/okhttp/spending")
public class SpendingOkhttpController {
    private final SpendingOkhttpService service;

    public SpendingOkhttpController(SpendingOkhttpService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody SpendingRequest spending) throws Exception {
        return service.createSpending(spending);
    }

//    @GetMapping("/{id}")
//    public String read(@PathVariable Long id) throws Exception {
//        return service.(id);
//    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @RequestBody SpendingRequest request) throws Exception {
        return service.updateSpending(request, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        return service.deleteSpending(id);
    }
}
