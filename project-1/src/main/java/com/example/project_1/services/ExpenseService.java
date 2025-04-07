//package com.example.project_1.services;
//
//
//import com.example.project_1.DTO.DailyExpenseDTO;
//import com.example.project_1.DTO.ExpenseDTO;
//import com.example.project_1.DTO.ExpenseDateRangeRequest;
//import com.example.project_1.DTO.MonthlyExpenseReport;
//import com.example.project_1.models.Expense;
//import com.example.project_1.models.User;
//import com.example.project_1.repos.ExpenseRepository;
//
//import com.example.project_1.repos.UserRepository;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import org.springframework.data.domain.Pageable;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ExpenseService {
//
//    private final ExpenseRepository expenseRepository;
//
//
//    private final UserRepository userRepository;
//
//    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
//        this.userRepository = userRepository;
//        this.expenseRepository = expenseRepository;
//    }
//
//
//    public Expense createExpense(ExpenseDTO expenseDTO) {
//        User user = userRepository.findById(expenseDTO.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Expense expense = new Expense();
//        expense.setUser(user);
//        expense.setAmount(expenseDTO.getAmount());
//        expense.setCategory(expenseDTO.getCategory());
//        expense.setDate(expenseDTO.getDate());
////        expense.setDescription(expense.getDescription());
//
//        return expenseRepository.save(expense);
//    }
//
////        public List<Expense> getUserExpenses(Long userId) {
////            if (!userRepository.existsById(userId)) {
////                return Collections.emptyList();
////            }
////
////            return expenseRepository.findByUserId(userId);
////        }
//
//
//    public Page<Expense> getUserExpenses(Long userId, Pageable pageable) {
//        if (!userRepository.existsById(userId)) {
//            return Page.empty();
//        }
//        return expenseRepository.findByUserId(userId, pageable);
//    }
//
//
//    @Cacheable(value = "monthlyExpenses", key = "#userId")
//    public Page<Expense> getMonthlyExpensesOfUser(Long userId, int year, int month, Pageable pageable) {
//        if (!userRepository.existsById(userId)) {
//            return Page.empty();
//        }
//
//        LocalDate startDate = LocalDate.of(year, month, 1);
//        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
//
//
//        return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate, pageable);
//    }
//
//
//    public List<Expense> getExpensesByDateRange(ExpenseDateRangeRequest request) {
//
//        if (!userRepository.existsById(request.getUserId())) {
//            return Collections.emptyList();
//        }
//
//        return expenseRepository.findByUserIdAndDateBetween(
//                request.getUserId(), request.getStartDate(), request.getEndDate()
//        );
////    }
//
//    }
//
//    //
////    public Double getMonthlyTotalExpenditure(Long userId, int year, int month) {
////        if (!userRepository.existsById(userId)) {
////            return 0.0;
////        }
////
////        List<Expense> expenses = expenseRepository.findByUserIdAndYearAndMonth(userId, year, month);
////
////        // Sum up all the amounts
////        return expenses.stream()
////                .mapToDouble(Expense::getAmount)
////                .sum();
////    }
//    @Cacheable(value = "monthlyTotal", key = "#request.userId")
//
//    public Double getMonthlyTotalExpenditure(MonthlyExpenseReport request) {
//        if (!userRepository.existsById(request.getUserId())) {
//            return 0.0;
//        }
//
//
//        List<Expense> expenses = expenseRepository.findByUserId(request.getUserId());
//
//
//        return expenses.stream()
//                .filter(expense -> {
//                    int expenseYear = expense.getDate().getYear();
//                    int expenseMonth = expense.getDate().getMonthValue();
//                    return expenseYear == request.getYear() && expenseMonth == request.getMonth();
//                })
//                .mapToDouble(Expense::getAmount)
//                .sum();
//    }
//
//
//    public Double getDailyTotalExpenditure(DailyExpenseDTO request) {
//        if (!userRepository.existsById(request.getUserId())) {
//            return 0.0;
//        }
//
//        // Fetch all expenses for the user
//        List<Expense> expenses = expenseRepository.findByUserId(request.getUserId());
//
//        // Filter by exact date
//        return expenses.stream()
//                .filter(expense -> expense.getDate().isEqual(request.getDate()))
//                .mapToDouble(Expense::getAmount)
//                .sum();
//    }
//
//    public List<Expense> getDailyExpenses(Long userId, LocalDate date) {
//        return expenseRepository.findByUserIdAndDate(userId, date);
//    }
//
//    public Double getTotalExpenditureByDateRange(ExpenseDateRangeRequest request) {
//
//        if (!userRepository.existsById(request.getUserId())) {
//            return 0.0;
//        }
//
//        List<Expense> expenses = expenseRepository.findByUserId(request.getUserId());
//
//
//        return expenses.stream()
//                .filter(expense -> !expense.getDate().isBefore(request.getStartDate())
//                        && !expense.getDate().isAfter(request.getEndDate()))
//                .mapToDouble(Expense::getAmount)
//                .sum();
//    }
//
//
//    public Map<String, Double> getMonthlyCategoryWiseTotal(MonthlyExpenseReport request) {
//
//        // Fetch all expenses for the given user, year, and month
//        List<Expense> expenses = expenseRepository.findByUserId(request.getUserId());
//
//        // Filter expenses based on the provided year and month
//        return expenses.stream()
//                .filter(expense -> expense.getDate().getYear() == request.getYear()
//                        && expense.getDate().getMonthValue() == request.getMonth())
//                .collect(Collectors.groupingBy(
//                        Expense::getCategory,                  // Group by category
//                        Collectors.summingDouble(Expense::getAmount)  // Sum amounts per category
//                ));
//    }
//
//    public Map<String, Double> getDailyCategoryWiseTotal(DailyExpenseDTO request) {
//
//
//        List<Expense> expenses = expenseRepository.findByUserId(request.getUserId());
//
//
//        return expenses.stream()
//                .filter(expense -> expense.getDate().isEqual(request.getDate())) // Filter by specific date
//                .collect(Collectors.groupingBy(
//                        Expense::getCategory,                  // Group by category
//                        Collectors.summingDouble(Expense::getAmount)  // Sum amounts per category
//                ));
//    }
//
//
//    @CacheEvict(value = "monthlyExpenses", key = "#result.userId")
//    public void deleteExpense(Long expenseId) {
//        if (!expenseRepository.existsById(expenseId)) {
//            throw new IllegalArgumentException("Expense not found with ID: " + expenseId);
//        }
//        expenseRepository.deleteById(expenseId);
//    }
//
//
//    @CachePut(value = "monthlyExpenses", key = "#userId")
//    public Expense updateExpense(ExpenseDTO expenseDTO) {
//        Optional<Expense> optionalExpense = expenseRepository.findByDateAndCategory(
//                expenseDTO.getDate(), expenseDTO.getCategory()
//        );
//
//        if (!optionalExpense.isPresent()) {
//            return null;
//        }
//
//        Expense expense = optionalExpense.get();
//        expense.setAmount(expenseDTO.getAmount());
//
//
//        return expenseRepository.save(expense);
//    }
//
//}
//
//
//
//
//
