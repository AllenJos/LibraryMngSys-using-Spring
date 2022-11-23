package com.spring.libraryMngSys.controller;

import com.spring.libraryMngSys.model.MyUser;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.request.StudentCreateRequest;
import com.spring.libraryMngSys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    //Create Student API
    @PostMapping("/student")
    public void createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }

    //Only for Student
    @GetMapping("/student")
    public Student getStudent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getStudent()==null){
            throw new Exception("User requesting the details is not a Student!");
        }
        int studentId = myUser.getStudent().getId();
        return studentService.findStudentByStudentId(studentId);

    }

    @GetMapping("/student-for-admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getAdmin()==null){
            throw new Exception("User requesting the details is not an Admin!");
        }

        return studentService.findStudentByStudentId(studentId);
    }
}
