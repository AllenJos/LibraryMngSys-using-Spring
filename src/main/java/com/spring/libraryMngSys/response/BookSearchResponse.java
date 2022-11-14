package com.spring.libraryMngSys.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.libraryMngSys.model.Author;
import com.spring.libraryMngSys.model.Genre;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.model.Transaction;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Class created to avoid circular dependency which leads to StackOverflow
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchResponse {

    private int id;
    private String name;
    private int cost;

    private Genre genre;

    private Student student;


    private List<Transaction> transactionList;

    @JsonIgnoreProperties("bookList")
    private Author author;


    private Date createdOn;

    private Date updatedOn;
}
