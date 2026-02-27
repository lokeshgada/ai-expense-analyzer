package com.lokesh.ai_expense_analyzer.service;

import com.lokesh.ai_expense_analyzer.entity.Expense;
import com.lokesh.ai_expense_analyzer.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        if (expense.getCreatedAt() == null) {
            expense.setCreatedAt(LocalDateTime.now());
        }
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public Expense updateExpense(String id, Expense expense) {
        Expense existing = getExpenseById(id);

        existing.setTitle(expense.getTitle());
        existing.setCategory(expense.getCategory());
        existing.setAmount(expense.getAmount());

        return expenseRepository.save(existing);
    }

    public void deleteExpense(String id) {
        // check first so you get clear message if id is wrong
        getExpenseById(id);
        expenseRepository.deleteById(id);
    }

    public Expense generateAiSummary(String id) {
        Expense expense = getExpenseById(id);

        String summary = "You spent $" + expense.getAmount()
                + " on " + expense.getTitle()
                + " under category " + expense.getCategory()
                + ". Consider tracking this category weekly to control spending.";

        expense.setAiSummary(summary);
        return expenseRepository.save(expense);
    }
    public int importExpensesFromCsv(MultipartFile file) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".csv")) {
            throw new RuntimeException("Only CSV files supported right now");
        }

        int count = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                // skip header line: title,category,amount
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String title = parts[0].trim();
                String category = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());

                Expense expense = new Expense();
                expense.setTitle(title);
                expense.setCategory(category);
                expense.setAmount(amount);

                saveExpense(expense);
                count++;
            }
        }

        return count;
    }
}

