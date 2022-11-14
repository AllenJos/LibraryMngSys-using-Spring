package com.spring.libraryMngSys.request;

import com.spring.libraryMngSys.model.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

//Handles Book details coming from the frontend
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    //to() function for creating Book object from BookCreateRequest object.
    public Book to(){
        return Book.builder()
                .name(this.name)
                .cost(this.cost)
                .genre(this.genre)
                .author(this.author)
                .build();
    }

}
