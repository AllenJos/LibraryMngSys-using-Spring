package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.model.MyUser;
import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.repository.StudentRepository;
import com.spring.libraryMngSys.request.StudentCreateRequest;
import com.spring.libraryMngSys.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Value("${users.student.authority}")
    String studentAuthority;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

//    public void create(StudentCreateRequest studentCreateRequest) {
//        MyUser myUser = MyUser.builder()
//                .authority(studentAuthority)
//                .username(studentCreateRequest.getUsername())
//                .password(studentCreateRequest.getPassword() )
//                .build();
//
//        myUser = myUserDetailsService.createUser(myUser);
//
//        Student student = studentCreateRequest.to();
//        student.setMyUser(myUser);
//        studentRepository.save(student);
//    }

    public void create(StudentCreateRequest studentCreateRequest) {
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

        Student student = studentCreateRequest.to();
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return student;
    }
}
