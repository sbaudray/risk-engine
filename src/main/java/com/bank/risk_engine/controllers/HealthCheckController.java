package com.bank.risk_engine.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/api/v1/health")
    public Map<String, Object> getHealthStatus() {
        return Map.of(
                "status", "UP",
                "sector", "Banking & Insurance",
                "timestamp", LocalDateTime.now(),
                "runtime", "Java 31");
    }
}
