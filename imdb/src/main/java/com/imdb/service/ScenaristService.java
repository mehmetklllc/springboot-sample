package com.imdb.service;

import com.imdb.domain.ScenaristCreateRequestDto;
import com.imdb.domain.ScenaristMoviesRequestDto;
import com.imdb.domain.entities.Movie;

import java.util.List;

public interface ScenaristService {

    void createScenarist(ScenaristCreateRequestDto scenaristCreateRequestDto);
    List<Movie> scenaristMovies(ScenaristMoviesRequestDto scenaristMoviesRequestDto);
}
