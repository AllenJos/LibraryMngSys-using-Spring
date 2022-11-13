package com.spring.libraryMngSys;

import com.spring.libraryMngSys.model.Author;
import com.spring.libraryMngSys.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LibraryMngSysApplication implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryMngSysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Author> authorList = authorRepository
//				.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(25, "India", "A");
//		authorList.forEach(obj-> System.out.println(obj.getName()));
	}
}

