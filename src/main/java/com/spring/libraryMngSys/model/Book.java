package com.spring.libraryMngSys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.libraryMngSys.response.BookSearchResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Author author;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;


    public BookSearchResponse to() {
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .cost(cost)
                .genre(genre)
                .student(student)
                .transactionList(transactionList)
                .author(author)
                .createdOn(createdOn)
                .updatedOn(updatedOn)
                .build();
    }
}
