package com.spring.libraryMngSys.repository;

import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String value);

    List<Book> findByAuthorName(String value);

    List<Book> findByGenre(Genre valueOf);
}
