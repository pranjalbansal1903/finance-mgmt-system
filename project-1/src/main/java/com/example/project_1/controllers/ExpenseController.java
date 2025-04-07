//package com.example.project_1.controllers;
//
//
//import com.example.project_1.DTO.*;
//import com.example.project_1.MyUserDetailsService;
//import com.example.project_1.models.Expense;
//import com.example.project_1.models.User;
//import com.example.project_1.services.ExpenseService;
//import com.example.project_1.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class ExpenseController {
//
//
//
//    @Autowired
//    private  ExpenseService expenseService;
//
//    @Autowired
//    private  UserService userService;
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//
//
//
//
//
//
//
//    @PostMapping("/create")
//    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDTO expenseDTO) {
//        Expense createdExpense = expenseService.createExpense(expenseDTO);
//        return ResponseEntity.ok(createdExpense);
//    }
//
//    @PostMapping("/all")
//    public ResponseEntity<Page<Expense>> getExpenses(@RequestBody AllExpenseDTO request) {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//
//
//        if (authenticatedUsername == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//
//        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
//
//
//        Page<Expense> expenses = expenseService.getUserExpenses(authenticatedUser.getId(), pageable);
//
//        return ResponseEntity.ok(expenses);
//    }
//
//
//
//
//    @DeleteMapping("/delete/{expenseId}")
//    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId) {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//
//
//        if (authenticatedUsername == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//
//
//        if (authenticatedUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // User not found
//        }
//
//
//
//
//
//        expenseService.deleteExpense(expenseId);
//        return ResponseEntity.ok("Expense deleted successfully!");
//    }
//
//
//
//    @PutMapping("/update")
//    public ResponseEntity<?> updateExpense(@RequestBody ExpenseDTO expenseDTO) {
//
//        String authenticatedUsername = getAuthenticatedUsername();
//
//
//
//        if (authenticatedUsername == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//
//
//        if (authenticatedUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        Expense updatedExpense = expenseService.updateExpense(expenseDTO);
//        if (updatedExpense != null) {
//            return ResponseEntity.ok(updatedExpense);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("No matching expense found for the given date and category.");
//        }
//    }
//
//
//
//    @PostMapping("/monthly")
//        public ResponseEntity<Page<Expense>> getMonthlyExpenses (@RequestBody MonthlyExpenseReport request){
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//
//            Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
//
//
//            Page<Expense> expenses = expenseService.getMonthlyExpensesOfUser(request.getUserId(), request.getYear(), request.getMonth(), pageable);
//
//
//
//            return ResponseEntity.ok(expenses);
//        }
//
//
//
//
//
//
//    @PostMapping("/monthly/total")
//    public ResponseEntity<Double> getMonthlyTotalExpenditure (@RequestBody MonthlyExpenseReport request){
//
//        String authenticatedUsername = getAuthenticatedUsername();
//
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//
//
//        if (authenticatedUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        return ResponseEntity.ok(expenseService.getMonthlyTotalExpenditure(request));
//    }
//
//        @PostMapping("/monthly/category/total")
//        public ResponseEntity<Map<String, Double>> getMonthlyCategoryWiseTotal (@RequestBody MonthlyExpenseReport
//        request){
//
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            return ResponseEntity.ok(expenseService.getMonthlyCategoryWiseTotal(request));
//        }
//
//
//        @PostMapping("/daily")
//        public ResponseEntity<List<Expense>> getDailyExpenses (@RequestBody DailyExpenseDTO request){
//
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            List<Expense> expenses = expenseService.getDailyExpenses(request.getUserId(), request.getDate());
//            return ResponseEntity.ok(expenses);
//        }
//
//
//        @PostMapping("/daily/total")
//        public ResponseEntity<Double> getDailyTotalExpenditure (@RequestBody DailyExpenseDTO request){
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            Double totalExpenditure = expenseService.getDailyTotalExpenditure(request);
//            return ResponseEntity.ok(totalExpenditure);
//        }
//
//        @PostMapping("/daily/category/total")
//        public ResponseEntity<Map<String, Double>> getDailyCategoryWiseTotal (@RequestBody DailyExpenseDTO request){
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            return ResponseEntity.ok(expenseService.getDailyCategoryWiseTotal(request));
//        }
//
//
//        @PostMapping("/daterange")
//        public ResponseEntity<?> getExpensesByDateRange (@RequestBody ExpenseDateRangeRequest request){
//            LocalDate startDate = request.getStartDate();
//            LocalDate endDate = request.getEndDate();
//
//            if (startDate == null || endDate == null) {
//                return ResponseEntity.badRequest().body("Start date and end date must not be null");
//            }
//
//            if (startDate.isAfter(endDate)) {
//                return ResponseEntity.badRequest().body("Start date must be before end date");
//            }
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            List<Expense> expenses = expenseService.getExpensesByDateRange(request);
//            return ResponseEntity.ok(expenses);
//        }
//
//        @PostMapping("/daterange/total")
//        public ResponseEntity<?> getTotalExpenditureByDateRange (@RequestBody ExpenseDateRangeRequest request){
//            LocalDate startDate = request.getStartDate();
//            LocalDate endDate = request.getEndDate();
//
//            if (startDate == null || endDate == null) {
//                return ResponseEntity.badRequest().body("Start date and end date must not be null");
//            }
//
//            if (startDate.isAfter(endDate)) {
//                return ResponseEntity.badRequest().body("Start date must be before end date");
//            }
//
//            String authenticatedUsername = getAuthenticatedUsername();
//
//
//            User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//            if (authenticatedUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            Double totalExpenditure = expenseService.getTotalExpenditureByDateRange(request);
//            return ResponseEntity.ok(totalExpenditure);
//        }
//
//    private String getAuthenticatedUsername() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
//        } else {
//            return principal.toString();
//        }
//
//    }
//
//
//
//
//
//    @GetMapping("/monthly/download")
//    public void downloadMonthlyExpensesCsv(@RequestParam int year, @RequestParam int month,
//                                           HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE); // Fetch all records
//        Page<Expense> expensesPage = expenseService.getMonthlyExpensesOfUser(
//                authenticatedUser.getId(), year, month, pageable);
//        List<Expense> expenses = expensesPage.getContent();
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=monthly_expenses_" + year + "_" + month + ".csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("ID,Date,Category,Amount");
//        for (Expense expense : expenses) {
//            writer.println(expense.getExpenseId() + "," +
//                    expense.getDate() + "," +
//                    expense.getCategory() + "," +
//                    expense.getAmount());
//
//        }
//        writer.flush();
//    }
//
//    @GetMapping("/all/download")
//    public void downloadAllExpensesCsv(HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE); // Fetch all records
//        Page<Expense> expensesPage = expenseService.getUserExpenses(authenticatedUser.getId(), pageable);
//        List<Expense> expenses = expensesPage.getContent();
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=all_expenses.csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("ID,Date,Category,Amount,Description");
//        for (Expense expense : expenses) {
//            writer.println(expense.getExpenseId() + "," +
//                    expense.getDate() + "," +
//                    expense.getCategory() + "," +
//                    expense.getAmount());
//        }
//        writer.flush();
//    }
//
//    @PostMapping("/monthly/total/download")
//    public void downloadMonthlyTotalExpenditureCsv(@RequestBody MonthlyExpenseReport request, HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//        Double totalExpenditure = expenseService.getMonthlyTotalExpenditure(request);
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=monthly_total_expenditure.csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("Year,Month,Total Expenditure");
//        writer.println(request.getYear() + "," + request.getMonth() + "," + totalExpenditure);
//        writer.flush();
//    }
//
//    @PostMapping("/daily/download")
//    public void downloadDailyExpensesCsv(@RequestBody DailyExpenseDTO request, HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//        List<Expense> expenses = expenseService.getDailyExpenses(request.getUserId(), request.getDate());
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=daily_expenses.csv");
//
//        PrintWriter writer = response.getWriter();
//        writer.println("ID,Date,Category,Amount,Description");
//        for (Expense expense : expenses) {
//            writer.println(expense.getExpenseId() + "," + expense.getDate() + "," + expense.getCategory() + "," +
//                    expense.getAmount());
//        }
//        writer.flush();
//    }
//
//    @PostMapping("/daily/total/download")
//    public void downloadDailyTotalExpenditureCsv(@RequestBody DailyExpenseDTO request, HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        Double totalExpenditure = expenseService.getDailyTotalExpenditure(request);
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=daily_total_expenditure.csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("Date,Total Expenditure");
//        writer.println(request.getDate() + "," + totalExpenditure);
//        writer.flush();
//    }
//
//    @PostMapping("/daily/category/total/download")
//    public void downloadDailyCategoryWiseTotalCsv(@RequestBody DailyExpenseDTO request, HttpServletResponse response) throws IOException {
//        String authenticatedUsername = getAuthenticatedUsername();
//
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        Map<String, Double> categoryWiseTotal = expenseService.getDailyCategoryWiseTotal(request);
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=daily_category_total_expenditure.csv");
//
//
//        PrintWriter writer = response.getWriter();
//
//        writer.println("Category,Total Expenditure");
//
//        for (Map.Entry<String, Double> entry : categoryWiseTotal.entrySet()) {
//            writer.println(entry.getKey() + "," + entry.getValue());
//        }
//
//        writer.flush();
//    }
//
//    @PostMapping("/daterange/download")
//    public void downloadExpensesByDateRangeCsv(@RequestBody ExpenseDateRangeRequest request, HttpServletResponse response) throws IOException {
//        LocalDate startDate = request.getStartDate();
//        LocalDate endDate = request.getEndDate();
//
//        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date range");
//            return;
//        }
//
//        String authenticatedUsername = getAuthenticatedUsername();
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        List<Expense> expenses = expenseService.getExpensesByDateRange(request);
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=expenses_by_daterange.csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("Date,Category,Amount,Description");
//
//        for (Expense expense : expenses) {
//            writer.println(expense.getDate() + "," +
//                    expense.getCategory() + "," +
//                    expense.getAmount()); // Handling commas in descriptions
//        }
//
//        writer.flush();
//    }
//
//    @PostMapping("/daterange/total/download")
//    public void downloadTotalExpenditureByDateRangeCsv(@RequestBody ExpenseDateRangeRequest request, HttpServletResponse response) throws IOException {
//        LocalDate startDate = request.getStartDate();
//        LocalDate endDate = request.getEndDate();
//
//        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date range");
//            return;
//        }
//
//        String authenticatedUsername = getAuthenticatedUsername();
//        if (authenticatedUsername == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not authenticated");
//            return;
//        }
//
//        User authenticatedUser = userService.getUserByUsername(authenticatedUsername);
//        if (authenticatedUser == null) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
//            return;
//        }
//
//
//        Double totalExpenditure = expenseService.getTotalExpenditureByDateRange(request);
//
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=total_expenditure_by_daterange.csv");
//
//
//        PrintWriter writer = response.getWriter();
//        writer.println("Start Date,End Date,Total Expenditure");
//        writer.println(startDate + "," + endDate + "," + totalExpenditure);
//
//        writer.flush();
//    }
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
