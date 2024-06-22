package com.imdb.service;

import com.imdb.domain.*;
import com.imdb.domain.entities.Company;
import com.imdb.domain.entities.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    void createMovie(MovieCreateRequestDto movieCreateRequestDto);
    void updateMovie(MovieUpdateRequestDto movieUpdateRequestDto);

    void deleteMovie(MovieDeleteRequestDto movieDeleteRequestDto);

    List<Movie> findByScenaristId(String scenaristId);

    List<Movie> allMovie();

    List<Movie> findByMovieList(List<String> movieIdList);

    MovieDetailResponseDto movieDetail(MovieDetailRequestDto movieDetailRequestDto);
}
