package com.spring.libraryMngSys.controller;

import com.spring.libraryMngSys.exception.TxnServiceException;
import com.spring.libraryMngSys.model.Transaction;
import com.spring.libraryMngSys.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    //issue Transaction API:
    // accepts bookId, studentId
    @PostMapping("/transaction/issue")
    public void issueTxn(@RequestParam("bookId") Integer bookId, @RequestParam("studentId") Integer studentId) throws TxnServiceException {
        transactionService.issueTransaction(bookId, studentId);
    }

    //return Transaction API:
    // accepts bookId, studentId
    @PostMapping("/transaction/return")
    public void returnTxn(@RequestParam("bookId") Integer bookId, @RequestParam("studentId") Integer studentId) throws TxnServiceException {
        transactionService.returnTransaction(bookId, studentId);
    }
}
