package com.lokesh.ai_expense_analyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private LocalDate date;        // Example: 2026-02-19
    private String description;     // Example: "Walmart Supercenter"
    private BigDecimal amount;      // Example: -45.20
    private String category;        // Example: "Groceries" (AI will fill later)
}