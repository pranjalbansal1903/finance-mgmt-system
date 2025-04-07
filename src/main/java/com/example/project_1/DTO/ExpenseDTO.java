package com.example.project_1.DTO;

import com.example.project_1.models.Expense;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseDTO {

    private Long userId;
    private String category;
    private Double amount;
    private LocalDate date;


    private Long expenseId;



//    public int getYear() {
//        this.
//    }
//
//    public int getMonth() {
//    }


//    private Double totalExpenseForMonth;// Extracted from `date`



}
