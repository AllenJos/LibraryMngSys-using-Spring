package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.model.Author;
import com.spring.libraryMngSys.model.Book;
import com.spring.libraryMngSys.model.Genre;
import com.spring.libraryMngSys.repository.AuthorRepository;
import com.spring.libraryMngSys.repository.BookRepository;
import com.spring.libraryMngSys.request.BookCreateRequest;
import com.spring.libraryMngSys.request.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    //Method: accepts BookCreateRequest and converts it into a Book object before
    //        saving it in DB using BookRepository
    public void create(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.to();

        //Find if the author is already present in the Author table.
        //If not save it in Author table first, then save book details in Book table.
        //If present we can directly save it in Book table after fetching from Author table.
//        Author authorFromDB = authorRepository.getAuthorByGivenEmailIdNative(book.getAuthor().getEmail());
        Author authorFromDB = authorRepository.findByEmail(book.getAuthor().getEmail());
        if(authorFromDB==null){
            authorFromDB = authorRepository.save(book.getAuthor());
        }

        //we have to execute this line again so that the foreign key gets populated properly.
        //i.e. the author_id is populated.
        book.setAuthor(authorFromDB);
        bookRepository.save(book);
    }

    //Method: method used mainly to update Book object
    public void create(Book book) {
        bookRepository.save(book);
    }

    public List<Book> find(BookFilterType bookFilterType, String value) {
        switch (bookFilterType){
            case NAME:
                return bookRepository.findByName(value);

            case AUTHOR_NAME:
                return bookRepository.findByAuthorName(value);

            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));

                //used Collections.singleton() because this method was return List of Books and
            //        we needed only one Book object.
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singleton(Integer.parseInt(value)));

            default:
                return new ArrayList<>();
        }
    }


}
