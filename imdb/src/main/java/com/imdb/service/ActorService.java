package com.imdb.service;

import com.imdb.domain.*;
import com.imdb.domain.entities.Actor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorService {

    void createActor(ActorCreateRequestDto actorCreateRequestDto);

    void updateActor(ActorUpdateRequestDto actorUpdateRequestDto);

    void deleteActor(ActorDeleteRequestDto actordeleteRequestDto);

    List<Actor> actors();

    ActorMoviesResponseDto actorMovies(ActorMoviesRequestDto actorMoviesRequestDto);
}
