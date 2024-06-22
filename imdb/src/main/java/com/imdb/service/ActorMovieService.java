package com.imdb.service;

import com.imdb.domain.entities.ActorMovie;

import java.util.List;

public interface ActorMovieService {

    void createActorMovie(String actorId,String movieId);

    List<ActorMovie> actorMovies(String actorId);

    List<ActorMovie> movieActors(String movieId);
}
