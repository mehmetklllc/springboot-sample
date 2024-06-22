package com.imdb.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class MovieUpdateRequestDto {
    private UUID movieId;
    private String movieName;
    private String releaseDate;
    private String desc;
    private String producer;
    private String budget;
    private String image;
    private String scenaristId;
}
