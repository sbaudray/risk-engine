package com.bank.risk_engine.config;

import org.springframework.stereotype.Component;

import com.bank.risk_engine.models.ApplicationStatus;
import com.bank.risk_engine.models.LoanApplication;
import com.bank.risk_engine.repositories.LoanApplicationRepository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final LoanApplicationRepository repository;

    public DatabaseSeeder(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            System.out.println("Database already contains data. Skipping seeding phase");
            return;
        }

        System.out.println("Seeding enterprise risk dataset into PostgreSQL");

        LoanApplication app1 = new LoanApplication();
        app1.setApplicantName("ALice Martin");
        app1.setMonthlyIncome(new BigDecimal("8500.00"));
        app1.setMonthlyDebtPayments(new BigDecimal("1200.00"));
        app1.setCreditScore(700);
        app1.setRequestedAmount(new BigDecimal("25000.00"));
        app1.setStatus(ApplicationStatus.APPROVED);

        LoanApplication app2 = new LoanApplication();
        app2.setApplicantName("Bob Lapointe");
        app2.setMonthlyIncome(new BigDecimal("3100.00"));
        app2.setMonthlyDebtPayments(new BigDecimal("2900.00"));
        app2.setCreditScore(520);
        app2.setRequestedAmount(new BigDecimal("95000.00"));
        app2.setStatus(ApplicationStatus.REJECTED);
        app2.setRejectionReason("Debt-to-Income ratio exceeds safety limits.");

        repository.saveAll(List.of(app1, app2));

        System.out.println("Data seeding complete! " + repository.count() + " records active.");
    }

}
