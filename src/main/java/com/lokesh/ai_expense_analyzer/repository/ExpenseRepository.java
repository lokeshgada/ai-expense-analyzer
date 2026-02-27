package com.lokesh.ai_expense_analyzer.repository;

import com.lokesh.ai_expense_analyzer.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String> {

}
