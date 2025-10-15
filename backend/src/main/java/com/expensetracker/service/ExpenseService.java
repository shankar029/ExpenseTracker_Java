package com.expensetracker.service;

import com.expensetracker.dto.request.ExpenseRequest;
import com.expensetracker.dto.response.ExpenseResponse;
import com.expensetracker.entity.Expense;
import com.expensetracker.entity.User;
import com.expensetracker.exception.BadRequestException;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.ExpenseRepository;
import com.expensetracker.util.ExpenseCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    public List<ExpenseResponse> getUserExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(this::convertToExpenseResponse)
                .collect(Collectors.toList());
    }

    public Page<ExpenseResponse> getUserExpenses(Long userId, int page, int size, String category, 
                                               LocalDate dateFrom, LocalDate dateTo) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Expense> expenses;

        if (category != null && dateFrom != null && dateTo != null) {
            ExpenseCategory expenseCategory = ExpenseCategory.fromString(category);
            expenses = expenseRepository.findByUserIdAndCategoryAndDateBetweenOrderByDateDesc(
                userId, expenseCategory, dateFrom, dateTo, pageable);
        } else if (category != null) {
            ExpenseCategory expenseCategory = ExpenseCategory.fromString(category);
            expenses = expenseRepository.findByUserIdAndCategoryOrderByDateDesc(
                userId, expenseCategory, pageable);
        } else if (dateFrom != null && dateTo != null) {
            expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(
                userId, dateFrom, dateTo, pageable);
        } else {
            expenses = expenseRepository.findByUserIdOrderByDateDesc(userId, pageable);
        }

        return expenses.map(this::convertToExpenseResponse);
    }

    public ExpenseResponse createExpense(Long userId, ExpenseRequest request) {
        User user = userService.findById(userId);
        
        ExpenseCategory category;
        try {
            category = ExpenseCategory.fromString(request.getCategory());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category: " + request.getCategory());
        }

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setCategory(category);
        expense.setDate(request.getDate());
        expense.setUser(user);

        expense = expenseRepository.save(expense);
        return convertToExpenseResponse(expense);
    }

    public ExpenseResponse getExpenseById(Long expenseId, Long userId) {
        Expense expense = expenseRepository.findById(expenseId)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        // Ensure the expense belongs to the authenticated user
        if (!expense.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Expense not found");
        }

        return convertToExpenseResponse(expense);
    }

    public ExpenseResponse updateExpense(Long expenseId, Long userId, ExpenseRequest request) {
        Expense expense = expenseRepository.findById(expenseId)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        // Ensure the expense belongs to the authenticated user
        if (!expense.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Expense not found");
        }

        ExpenseCategory category;
        try {
            category = ExpenseCategory.fromString(request.getCategory());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid category: " + request.getCategory());
        }

        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setCategory(category);
        expense.setDate(request.getDate());

        expense = expenseRepository.save(expense);
        return convertToExpenseResponse(expense);
    }

    public void deleteExpense(Long expenseId, Long userId) {
        Expense expense = expenseRepository.findById(expenseId)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        // Ensure the expense belongs to the authenticated user
        if (!expense.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Expense not found");
        }

        expenseRepository.delete(expense);
    }

    private ExpenseResponse convertToExpenseResponse(Expense expense) {
        return new ExpenseResponse(
            expense.getId(),
            expense.getAmount(),
            expense.getDescription(),
            expense.getCategory().getDisplayName(),
            expense.getDate(),
            expense.getCreatedAt(),
            expense.getUpdatedAt()
        );
    }
}