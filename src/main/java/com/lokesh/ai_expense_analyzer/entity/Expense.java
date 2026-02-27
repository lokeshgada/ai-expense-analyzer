package com.lokesh.ai_expense_analyzer.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data   // Lombok generates getters and setters automatically
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;

    private String title;

    private String category;

    private double amount;

    private LocalDateTime createdAt;

    // ADD THIS FIELD (THIS FIXES YOUR ERROR)
    private String aiSummary;

}
