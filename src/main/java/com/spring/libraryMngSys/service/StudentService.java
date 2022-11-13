package com.spring.libraryMngSys.service;

import com.spring.libraryMngSys.model.Student;
import com.spring.libraryMngSys.repository.StudentRepository;
import com.spring.libraryMngSys.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void create(StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.to();
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return student;
    }
}
