package com.spring.libraryMngSys.request;

import com.spring.libraryMngSys.model.AccountStatus;
import com.spring.libraryMngSys.model.Student;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String contact;
    private String address;

    private String email;

    public Student to() {
        return Student.builder()
                .name(name)
                .contact(contact)
                .address(address)
                .accountStatus(AccountStatus.ACTIVE)
                .email(email)
                .build();
    }
}
