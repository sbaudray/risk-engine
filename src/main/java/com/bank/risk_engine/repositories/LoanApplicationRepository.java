package com.bank.risk_engine.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.risk_engine.models.ApplicationStatus;
import com.bank.risk_engine.models.LoanApplication;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {
    List<LoanApplication> findByStatus(ApplicationStatus status);

    List<LoanApplication> findByCreditScoreLessThan(Integer score);
}
