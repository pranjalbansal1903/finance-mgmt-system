package com.example.project_1.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (amount > 0)")
    private Double amount;

    @Column
    private String category;


    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "fk_userid", nullable = false)  // Foreign key for user
    @JsonBackReference
    private User user;

    @Transient
    private LocalDate startDate;

    @Transient
    private LocalDate endDate;









}
