package com.bank.risk_engine.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.bank.risk_engine.dtos.LoanRequestDTO;
import com.bank.risk_engine.models.ApplicationStatus;
import com.bank.risk_engine.models.LoanApplication;
import com.bank.risk_engine.repositories.LoanApplicationRepository;

@Service
public class LoanRiskService {
    private static final int MINIMUM_CREDIT_SCORE = 600;
    private static final BigDecimal MAX_DEBT_TO_INCOME_RATIO = new BigDecimal("0.43");

    private final LoanApplicationRepository repository;

    public LoanRiskService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanApplication evaluateLoanApplication(LoanRequestDTO request) {
        LoanApplication application = new LoanApplication();
        application.setApplicantName(request.getApplicantName());
        application.setMonthlyIncome(request.getMonthlyIncome());
        application.setMonthlyDebtPayments(request.getMonthlyDebtPayments());
        application.setCreditScore(request.getCreditScore());
        application.setRequestedAmount(request.getRequestedAmount());

        if (application.getCreditScore() < 600) {
            application.setStatus(ApplicationStatus.REJECTED);
            application.setRejectionReason("Credit score falls below minimum threshold of " + MINIMUM_CREDIT_SCORE);
            return repository.save(application);
        }

        BigDecimal debtToIncomeRatio = application
                .getMonthlyDebtPayments()
                .divide(application.getMonthlyIncome(), 4, RoundingMode.HALF_EVEN);

        BigDecimal debtToIncomeThreshold = new BigDecimal("0.43");

        if (debtToIncomeRatio.compareTo(debtToIncomeThreshold) > 0) {
            application.setStatus(ApplicationStatus.REJECTED);
            application.setRejectionReason("Debt-to-Income ratio (" + debtToIncomeRatio.multiply(new BigDecimal(100))
                    + "%) exceeds maximum limit of" + MAX_DEBT_TO_INCOME_RATIO.multiply(new BigDecimal(100)) + "%");

            return repository.save(application);
        }

        application.setStatus(ApplicationStatus.APPROVED);
        return repository.save(application);
    }
}
