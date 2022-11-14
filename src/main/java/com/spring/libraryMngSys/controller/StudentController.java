package com.spring.libraryMngSys.controller;

import com.spring.libraryMngSys.request.StudentCreateRequest;
import com.spring.libraryMngSys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    //Create Student API
    @PostMapping("/student")
    public void createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }
}
