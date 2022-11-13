package com.spring.libraryMngSys.repository;


import com.spring.libraryMngSys.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    //method implementation using Native Query
    @Query(value = "select * from author a where a.email=?1", nativeQuery = true)
    Author getAuthorByGivenEmailIdNative(String email);

    //method implementation using JPQL
    @Query("select a from Author a where a.email=?1")
    Author getAuthorByGivenEmailIdJPQL(String email);

    //Hibernate
    Author findByEmail(String authorEmail);

    //Eg: Find all authors of age 25 and above, living in India, and Name starting with "A"
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(int age, String country, String prefix);
}
