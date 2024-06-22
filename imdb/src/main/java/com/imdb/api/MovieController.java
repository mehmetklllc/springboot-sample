package com.imdb.api;

import com.imdb.domain.MovieCreateRequestDto;
import com.imdb.domain.MovieDeleteRequestDto;
import com.imdb.domain.MovieDetailRequestDto;
import com.imdb.domain.MovieUpdateRequestDto;
import com.imdb.domain.dto.ImdbResponseCode;
import com.imdb.domain.dto.ImdbResponseEntity;
import com.imdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMovie(@RequestBody MovieCreateRequestDto movieCreateRequestDto) {

        movieService.createMovie(movieCreateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.CREATED, null);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateMovie(@RequestBody MovieUpdateRequestDto movieUpdateRequestDto) {

        movieService.updateMovie(movieUpdateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, null);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteMovie(@RequestBody MovieDeleteRequestDto movieDeleteRequestDto) {

        movieService.deleteMovie(movieDeleteRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, null);

    }

    @GetMapping("/movies")
    public ResponseEntity<Object> movies() {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, movieService.allMovie());

    }

    @PostMapping("/movie-detail")
    public ResponseEntity<Object> movieDetail(@RequestBody MovieDetailRequestDto movieDetailRequestDto) {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, movieService.movieDetail(movieDetailRequestDto));

    }

}
