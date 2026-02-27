package com.lokesh.ai_expense_analyzer.controller;

import com.lokesh.ai_expense_analyzer.entity.Expense;
import com.lokesh.ai_expense_analyzer.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getById(@PathVariable String id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }

    @PostMapping("/{id}/ai-summary")
    public Expense generateAiSummary(@PathVariable String id) {
        return expenseService.generateAiSummary(id);
    }
}
