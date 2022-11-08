package com.spring.libraryMngSys.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book my_book;

    @ManyToOne
    @JoinColumn
    private Student my_student;

    @CreationTimestamp
    private Date transactionDate;


}
