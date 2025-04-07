package com.example.project_1.repos;

import com.example.project_1.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Long> {



        Page<Expense> findByUserId(Long userId, Pageable pageable);

        List<Expense> findByUserId(Long userId);

        Page<Expense> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate, Pageable pageable);

        List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

        List<Expense> findByUserIdAndDate(Long userId, LocalDate date);

    Optional<Expense> findByDateAndCategory(LocalDate date, String category);


}


