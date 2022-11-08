package com.spring.libraryMngSys.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String contact;
    private String address;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(unique = true)
    private String email;

    private Date createdOn;
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    @OneToMany(mappedBy = "my_student")
    private List<Transaction> transactionList;
}
