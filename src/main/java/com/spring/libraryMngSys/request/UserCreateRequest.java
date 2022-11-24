package com.spring.libraryMngSys.request;

import com.spring.libraryMngSys.model.Admin;
import com.spring.libraryMngSys.model.Student;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;

}
