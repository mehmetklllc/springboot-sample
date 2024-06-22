package com.imdb.domain;

import lombok.Data;

@Data
public class MovieCreateRequestDto {
    private String movieName;
    private String releaseDate;
    private String desc;
    private String producer;
    private String budget;
    private String image;
    private String scenaristId;
}
