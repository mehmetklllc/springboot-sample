package com.imdb.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class ActorUpdateRequestDto {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String movieId;
}
