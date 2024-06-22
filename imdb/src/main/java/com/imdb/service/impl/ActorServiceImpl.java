package com.imdb.service.impl;

import com.imdb.config.LocalizationResolver;
import com.imdb.data.ActorRepository;
import com.imdb.domain.*;
import com.imdb.domain.dto.ImdbResultMessage;
import com.imdb.domain.entities.Actor;
import com.imdb.domain.entities.ActorMovie;
import com.imdb.service.ActorMovieService;
import com.imdb.service.ActorService;
import com.imdb.service.MovieService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieService movieService;

    private final ActorMovieService actorMovieService;

    private final LocalizationResolver localizationResolver;

    public ActorServiceImpl(ActorRepository actorRepository, MovieService movieService, ActorMovieService actorMovieService, LocalizationResolver localizationResolver) {
        this.actorRepository = actorRepository;
        this.movieService = movieService;
        this.actorMovieService = actorMovieService;
        this.localizationResolver = localizationResolver;
    }

    @Override
    public void createActor(ActorCreateRequestDto actorCreateRequestDto) {
        Actor actor = new Actor();
        actor.setFirstName(actorCreateRequestDto.getFirstName());
        actor.setLastName(actorCreateRequestDto.getLastName());
        actor.setBirthDate(actorCreateRequestDto.getBirthDate());
        actorRepository.save(actor);

    }

    @Override
    public void updateActor(ActorUpdateRequestDto actorUpdateRequestDto) {
        Optional<Actor> actor = actorRepository.findById(actorUpdateRequestDto.getUuid());
        if (actor.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.ACTOR_NOT_FOUND));
        Actor actorEnt=actor.get();
        actorEnt.setFirstName(actorUpdateRequestDto.getFirstName());
        actorEnt.setLastName(actorUpdateRequestDto.getLastName());
        actorEnt.setBirthDate(actorUpdateRequestDto.getBirthDate());
        actorMovieService.createActorMovie(actorEnt.getId().toString(), actorUpdateRequestDto.getMovieId());
        actorRepository.save(actorEnt);
    }

    @Override
    public void deleteActor(ActorDeleteRequestDto actorDeleteRequestDto) {

        Optional<Actor> actor = actorRepository.findById(actorDeleteRequestDto.getUuid());
        if (actor.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.ACTOR_NOT_FOUND));
        Actor actorEnt=actor.get();

        actorRepository.delete(actorEnt);
    }

    @Override
    public List<Actor> actors() {
        return actorRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @Override
    public ActorMoviesResponseDto actorMovies(ActorMoviesRequestDto actorMoviesRequestDto) {

        Optional<Actor> actor = actorRepository.findById(actorMoviesRequestDto.getActorId());
        if (actor.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.ACTOR_NOT_FOUND));
        Actor actorEnt=actor.get();

        List<ActorMovie> actorMovies = actorMovieService.actorMovies(actorEnt.getId().toString());
        List<String> moviesIdList = actorMovies.stream()
                .map(ActorMovie::getMovieId)
                .collect(Collectors.toList());
        ActorMoviesResponseDto actorMoviesResponseDto = new ActorMoviesResponseDto();
        actorMoviesResponseDto.setActorId(actorEnt.getId().toString());
        actorMoviesResponseDto.setActorName(actorEnt.getFirstName() + " " + actorEnt.getLastName());
        actorMoviesResponseDto.setActorMoviesIds(moviesIdList);


        return actorMoviesResponseDto;
    }
}
