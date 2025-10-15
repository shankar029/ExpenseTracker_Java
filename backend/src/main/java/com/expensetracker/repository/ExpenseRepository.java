package com.expensetracker.repository;

import com.expensetracker.entity.Expense;
import com.expensetracker.util.ExpenseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    Page<Expense> findByUserIdOrderByDateDesc(Long userId, Pageable pageable);
    
    Page<Expense> findByUserIdAndCategoryOrderByDateDesc(Long userId, ExpenseCategory category, Pageable pageable);
    
    Page<Expense> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable);
    
    Page<Expense> findByUserIdAndCategoryAndDateBetweenOrderByDateDesc(Long userId, ExpenseCategory category, LocalDate startDate, LocalDate endDate, Pageable pageable);
    
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    
    List<Expense> findByUserId(Long userId);
}