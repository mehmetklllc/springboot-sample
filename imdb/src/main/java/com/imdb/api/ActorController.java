package com.imdb.api;

import com.imdb.domain.*;
import com.imdb.domain.dto.ImdbResponseCode;
import com.imdb.domain.dto.ImdbResponseEntity;
import com.imdb.domain.entities.Actor;
import com.imdb.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createActor(@RequestBody ActorCreateRequestDto actorCreateRequestDto) {
        actorService.createActor(actorCreateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.CREATED, null);

    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateActor(@RequestBody ActorUpdateRequestDto actorUpdateRequestDto) {
        actorService.updateActor(actorUpdateRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, null);

    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteActor(@RequestBody ActorDeleteRequestDto actorDeleteRequestDto) {
        actorService.deleteActor(actorDeleteRequestDto);
        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, null);

    }

    @GetMapping("/actors")
    public ResponseEntity<Object> actors() {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, actorService.actors());

    }

    @PostMapping("/actor-movies")
    public ResponseEntity<Object> actorMovies(@RequestBody ActorMoviesRequestDto actorMoviesRequestDto) {

        return ImdbResponseEntity.response(ImdbResponseCode.SUCCESS.getCode(), ImdbResponseCode.SUCCESS.getMessage(), HttpStatus.OK, actorService.actorMovies(actorMoviesRequestDto));

    }

}
