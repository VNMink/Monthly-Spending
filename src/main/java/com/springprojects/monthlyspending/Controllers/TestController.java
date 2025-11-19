package com.springprojects.monthlyspending.Controllers;

import com.springprojects.monthlyspending.Constants.ApiResponse;
import com.springprojects.monthlyspending.Dtos.test.TestRequest;
import com.springprojects.monthlyspending.Services.okhttp.CkanOkhttpServiceTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    private final CkanOkhttpServiceTest service;

    public TestController(CkanOkhttpServiceTest service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createOrganization(
            @RequestHeader("Authorization") String token,
            @RequestBody TestRequest test) throws IOException {
        return ResponseEntity.ok(service.createOrganization(test, token));
    }
}
