package com.imdb.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class MovieDeleteRequestDto {

    private UUID movieId;
}
