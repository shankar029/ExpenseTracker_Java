package com.expensetracker.dto.response;

import java.util.List;

public class ExpenseListResponse {

    private List<ExpenseResponse> expenses;

    // Constructors
    public ExpenseListResponse() {}

    public ExpenseListResponse(List<ExpenseResponse> expenses) {
        this.expenses = expenses;
    }

    // Getters and Setters
    public List<ExpenseResponse> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseResponse> expenses) {
        this.expenses = expenses;
    }
}