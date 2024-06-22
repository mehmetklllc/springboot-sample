package com.imdb.domain;

import com.imdb.domain.entities.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieDetailResponseDto {
    private Movie movie;
    private List<String> movieActorsIds;
    private List<String> movieCompanyIds;
}
