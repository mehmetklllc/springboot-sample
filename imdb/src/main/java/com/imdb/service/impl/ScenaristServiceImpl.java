package com.imdb.service.impl;

import com.imdb.config.LocalizationResolver;
import com.imdb.data.MovieRepository;
import com.imdb.data.ScenaristRepository;
import com.imdb.domain.ScenaristCreateRequestDto;
import com.imdb.domain.ScenaristMoviesRequestDto;
import com.imdb.domain.dto.ImdbResultMessage;
import com.imdb.domain.entities.Company;
import com.imdb.domain.entities.Movie;
import com.imdb.domain.entities.Scenarist;
import com.imdb.service.ScenaristService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScenaristServiceImpl implements ScenaristService {

    private final MovieRepository movieRepository;
    private final LocalizationResolver localizationResolver;
    private final ScenaristRepository scenaristRepository;

    public ScenaristServiceImpl(MovieRepository movieRepository, LocalizationResolver localizationResolver, ScenaristRepository scenaristRepository) {
        this.movieRepository = movieRepository;
        this.scenaristRepository = scenaristRepository;
        this.localizationResolver = localizationResolver;
    }

    @Override
    public void createScenarist(ScenaristCreateRequestDto scenaristCreateRequestDto) {
        Scenarist scenarist = new Scenarist();
        scenarist.setFirstName(scenaristCreateRequestDto.getFirstName());
        scenarist.setLastName(scenaristCreateRequestDto.getLastName());
        scenaristRepository.save(scenarist);
    }

    @Override
    public List<Movie> scenaristMovies(ScenaristMoviesRequestDto scenaristMoviesRequestDto) {
        Optional<Scenarist> scenaristEnt = scenaristRepository.findById(scenaristMoviesRequestDto.getScenaristId());
        if (scenaristEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.SCENARIST_NOT_FOUND));
        Scenarist scenarist = scenaristEnt.get();

        List<Movie> movies = movieRepository.findByScenaristId(scenarist.getId().toString());
        return movies;
    }
}