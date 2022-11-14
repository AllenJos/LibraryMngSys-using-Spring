package com.spring.libraryMngSys.repository;

import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.model.Transaction;
import com.spring.libraryMngSys.request.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTopByStudentAndBookAndTransactionTypeOrderByTransactionDateDesc(Student student, Book book, TransactionType transactionType);
}
