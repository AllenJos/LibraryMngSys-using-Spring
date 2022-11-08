package com.spring.libraryMngSys.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @CreationTimestamp
    private Date createdOn;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;
}
