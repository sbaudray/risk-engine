package com.bank.risk_engine.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bank.risk_engine.dtos.LoanRequestDTO;
import com.bank.risk_engine.models.ApplicationStatus;
import com.bank.risk_engine.models.LoanApplication;
import com.bank.risk_engine.repositories.LoanApplicationRepository;

public class LoanRiskServiceTest {
    private LoanApplicationRepository repository;
    private LoanRiskService riskService;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(LoanApplicationRepository.class);

        riskService = new LoanRiskService(repository);

        when(repository.save(any(LoanApplication.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void should_RejectApplication_When_CreditScoreIsTooLow() {
        LoanRequestDTO request = new LoanRequestDTO();
        request.setApplicantName("Test Candidate");
        request.setCreditScore(550); // Below 600 limit
        request.setMonthlyIncome(new BigDecimal("5000"));
        request.setMonthlyDebtPayments(new BigDecimal("1000"));
        request.setRequestedAmount(new BigDecimal("10000"));

        LoanApplication result = riskService.evaluateLoanApplication(request);

        assertEquals(ApplicationStatus.REJECTED, result.getStatus());
        assertNotNull(result.getRejectionReason());
        assertTrue(result.getRejectionReason().contains("minimum threshold"));
    }
}
