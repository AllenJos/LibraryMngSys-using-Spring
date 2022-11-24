package com.spring.libraryMngSys;

import com.spring.libraryMngSys.model.Admin;
import com.spring.libraryMngSys.model.Author;
import com.spring.libraryMngSys.model.MyUser;
import com.spring.libraryMngSys.repository.AdminRepository;
import com.spring.libraryMngSys.repository.AuthorRepository;
import com.spring.libraryMngSys.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class LibraryMngSysApplication implements CommandLineRunner {

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LibraryMngSysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//creating first admin and then commenting from the 2nd runs to avoid duplicate username entry exception
//		MyUser myUser = MyUser.builder()
//				.username("raj")
//				.password(passwordEncoder.encode("raj123"))
//				.authority("adm")
//				.build();
//		myUserRepository.save(myUser);
//
//		Admin admin = Admin.builder()
//				.name("Raj Shukla")
//				.age(40)
//				.myUser(myUser)
//				.build();
//		adminRepository.save(admin);
	}
}

