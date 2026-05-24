package com.bank.risk_engine.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class LoanRequestDTO {
    @NotBlank(message = "Applicant name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String applicantName;

    @NotNull(message = "Monthly income is required")
    @PositiveOrZero(message = "Monthly income must be zero or a positive amount")
    private BigDecimal monthlyIncome;

    @NotNull(message = "Monthly debt payments are required")
    @PositiveOrZero(message = "Monthly debt payments must be zero or positive")
    private BigDecimal monthlyDebtPayments;

    @NotNull(message = "Credit score is required")
    @Min(value = 300, message = "Minimum credit score is 300")
    @Max(value = 850, message = "Maximum credit score is 850")
    private Integer creditScore;

    @NotNull(message = "Requested loan amount is required")
    @Positive(message = "Requested amount must be greater than zero")
    private BigDecimal requestedAmount;

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public BigDecimal getMonthlyDebtPayments() {
        return monthlyDebtPayments;
    }

    public void setMonthlyDebtPayments(BigDecimal monthlyDebtPayments) {
        this.monthlyDebtPayments = monthlyDebtPayments;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

}
