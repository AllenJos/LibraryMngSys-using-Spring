package com.spring.libraryMngSys.controller;

import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.request.BookFilterType;
import com.spring.libraryMngSys.request.BookCreateRequest;
import com.spring.libraryMngSys.response.BookSearchResponse;
import com.spring.libraryMngSys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.create(bookCreateRequest);
    }

    //localhost:9000/books/search?filter=AUTHOR_NAME&value=Allen
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType,
                                              @RequestParam("value") String value){
        return bookService.find(bookFilterType, value).stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

}
