package com.example.project_1.DTO;

import com.example.project_1.models.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyExpenseReport {
    private Long userId;
    private int  year;
    private int  month;

    private int page;
    private int size;
}




