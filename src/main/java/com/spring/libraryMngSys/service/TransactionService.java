package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.exception.TxnServiceException;
import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.model.Transaction;
import com.spring.libraryMngSys.repository.TransactionRepository;
import com.spring.libraryMngSys.request.BookFilterType;
import com.spring.libraryMngSys.request.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TransactionRepository transactionRepository;
    public void issueTransaction(Integer bookId, Integer studentId) throws TxnServiceException {
        /**
         * 1] check if student is valid entity
         * 2] check if book is present and available
         * 3] create issue transaction ==> saving transaction into transaction table
         * 4] Make a book unavailable
         */
        Student student = studentService.findStudentByStudentId(studentId);
        if(student==null){
            throw new TxnServiceException("Student is not present!");
        }

        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(bookList==null || bookList.size()!=1 || bookList.get(0).getStudent()!=null){
            throw new TxnServiceException("Book is not present!");
        }

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(bookList.get(0).getCost())
                .book(bookList.get(0))
                .student(student)
                .build();

        transactionRepository.save(transaction);

        bookList.get(0).setStudent(student);
        bookService.create(bookList.get(0));


    }
}
