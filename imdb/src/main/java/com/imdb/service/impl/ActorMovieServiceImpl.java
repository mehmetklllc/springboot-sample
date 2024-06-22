package com.imdb.service.impl;

import com.imdb.data.ActorMovieRepository;
import com.imdb.domain.entities.ActorMovie;
import com.imdb.service.ActorMovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorMovieServiceImpl implements ActorMovieService {

    private final ActorMovieRepository actorMovieRepository;

    public ActorMovieServiceImpl(ActorMovieRepository actorMovieRepository) {
        this.actorMovieRepository = actorMovieRepository;
    }


    @Override
    public void createActorMovie(String actorId, String movieId) {
        ActorMovie actorMovie = new ActorMovie();
        actorMovie.setActorId(actorId);
        actorMovie.setMovieId(movieId);
        actorMovieRepository.save(actorMovie);
    }

    @Override
    public List<ActorMovie> actorMovies(String actorId) {
        return actorMovieRepository.findByActorId(actorId);
    }

    @Override
    public List<ActorMovie> movieActors(String movieId) {
        return actorMovieRepository.findByMovieId(movieId);
    }
}
