package com.imdb.domain;

import lombok.Data;

@Data
public class ActorCreateRequestDto {

    private String firstName;
    private String lastName;
    private String birthDate;

}
