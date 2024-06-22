package com.imdb.api;

import com.imdb.domain.ScenaristCreateRequestDto;
import com.imdb.domain.ScenaristMoviesRequestDto;
import com.imdb.domain.dto.ImdbResponseCode;
import com.imdb.domain.dto.ImdbResponseEntity;
import com.imdb.service.ScenaristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scenarist")
public class ScenaristController {

    private final ScenaristService scenaristService;

    public ScenaristController(ScenaristService scenaristService) {
        this.scenaristService = scenaristService;
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createMovie(@RequestBody ScenaristCreateRequestDto scenaristCreateRequestDto) {
        scenaristService.createScenarist(scenaristCreateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.CREATED, null);
    }

    @PostMapping("/scenarist-movies")
    public ResponseEntity<Object> scenaristMovies(@RequestBody ScenaristMoviesRequestDto scenaristMoviesRequestDto) {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, scenaristService.scenaristMovies(scenaristMoviesRequestDto));
    }
}
