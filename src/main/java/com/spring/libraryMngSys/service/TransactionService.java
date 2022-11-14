package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.exception.TxnServiceException;
import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.model.Transaction;
import com.spring.libraryMngSys.repository.TransactionRepository;
import com.spring.libraryMngSys.request.BookFilterType;
import com.spring.libraryMngSys.request.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${book.return.due-date}")
    Integer number_of_days;

    public void issueTransaction(Integer bookId, Integer studentId) throws TxnServiceException {
        /**
         * 1] check if student is valid entity
         * 2] check if book is present and available (studentId should be null for the book to be available)
         * 3] create issue transaction ==> saving transaction into transaction table
         * 4] Make a book unavailable
         */
        //1]
        Student student = studentService.findStudentByStudentId(studentId);
        if(student==null){
            throw new TxnServiceException("Student is not present!");
        }

        //2]
        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(bookList==null || bookList.size()!=1 || bookList.get(0).getStudent()!=null){
            throw new TxnServiceException("Book is not present!");
        }

        //3]
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(bookList.get(0).getCost())
                .book(bookList.get(0))
                .student(student)
                .build();
        transactionRepository.save(transaction);

        //4]
        bookList.get(0).setStudent(student);
        bookService.create(bookList.get(0));


    }

    public String returnTransaction(Integer bookId, Integer studentId) throws TxnServiceException {
        /**
         * 1] Student is a valid entity.
         * 2] Book is issued to this particular student.
         * 3] Calculate a fine, if there.
         * 4] Create a return transaction.
         * 5] Make the book available.
         *
         */

        //1]
        Student student = studentService.findStudentByStudentId(studentId);
        if(student==null){
            throw new TxnServiceException("Student is not present!");
        }

        //2]
        List<Book> bookList = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(bookList==null || bookList.size()!=1){
            throw new TxnServiceException("Book is not present!");
        }
        //checking if the student is the same to whom this book was issued
        if( bookList.get(0).getStudent().getId()!=student.getId()){
            throw new TxnServiceException("Book not issued to this student!");
        }

        //3]
        //problem:
        //S1 ==> B1 issue ==> 10 May
        //S1 ==> B1 return ==> 15 May
        //S1 ==> B1 issue ==> 20 May

        Transaction issuedTransaction = transactionRepository
                .findTopByStudentAndBookAndTransactionTypeOrderByTransactionDateDesc(
                        student, bookList.get(0), TransactionType.ISSUE);

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .student(student)
                .book(bookList.get(0))
                .payment(calculateFine(issuedTransaction)) //calculate fine
                .build();
        transactionRepository.save(transaction);

        //make book available
        bookList.get(0).setStudent(null);
        bookService.create(bookList.get(0));

        return transaction.getExternalTxnId();
    }

    private double calculateFine(Transaction transaction) {
        long issueTime = transaction.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issueTime;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed>number_of_days){
            return (daysPassed-number_of_days) * 1.0;
        }

        return 0.0;
    }
}
