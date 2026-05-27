package com.bank.risk_engine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.risk_engine.dtos.LoanRequestDTO;
import com.bank.risk_engine.models.LoanApplication;
import com.bank.risk_engine.services.LoanRiskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanApplicationController {
    private final LoanRiskService riskService;

    public LoanApplicationController(LoanRiskService riskService) {
        this.riskService = riskService;
    }

    @PostMapping
    public ResponseEntity<LoanApplication> submitLoanApplication(@Valid @RequestBody LoanRequestDTO requestDTO) {
        LoanApplication processedApplication = riskService.evaluateLoanApplication(requestDTO);

        return new ResponseEntity<>(processedApplication, HttpStatus.CREATED);
    }
}
