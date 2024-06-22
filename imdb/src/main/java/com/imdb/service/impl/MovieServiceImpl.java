package com.imdb.service.impl;

import com.imdb.config.LocalizationResolver;
import com.imdb.data.MovieRepository;
import com.imdb.domain.*;
import com.imdb.domain.dto.ImdbResultMessage;
import com.imdb.domain.entities.ActorMovie;
import com.imdb.domain.entities.CompanyMovie;
import com.imdb.domain.entities.Movie;
import com.imdb.service.ActorMovieService;
import com.imdb.service.CompanyMovieService;
import com.imdb.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorMovieService actorMovieService;

    private final CompanyMovieService companyMovieService;

    private final LocalizationResolver localizationResolver;

    public MovieServiceImpl(MovieRepository movieRepository, ActorMovieService actorMovieService, CompanyMovieService companyMovieService, LocalizationResolver localizationResolver) {
        this.movieRepository = movieRepository;
        this.actorMovieService = actorMovieService;
        this.companyMovieService = companyMovieService;
        this.localizationResolver = localizationResolver;
    }

    @Override
    public void createMovie(MovieCreateRequestDto movieCreateRequestDto) {
        Movie movie = new Movie();
        movie.setMovieName(movieCreateRequestDto.getMovieName());
        movie.setBudget(movieCreateRequestDto.getBudget());
        movie.setDesc(movieCreateRequestDto.getDesc());
        movie.setScenaristId(movieCreateRequestDto.getScenaristId());
        movie.setReleaseDate(movieCreateRequestDto.getReleaseDate());
        movie.setImage(movieCreateRequestDto.getImage());
        movie.setProducer(movieCreateRequestDto.getProducer());

        movieRepository.save(movie);

    }

    @Override
    public void updateMovie(MovieUpdateRequestDto movieUpdateRequestDto) {
        Optional<Movie> movieEnt = movieRepository.findById(movieUpdateRequestDto.getMovieId());
        if (movieEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.MOVIE_NOT_FOUND));
        Movie movie = movieEnt.get();

        movie.setMovieName(movieUpdateRequestDto.getMovieName());
        movie.setBudget(movieUpdateRequestDto.getBudget());
        movie.setDesc(movieUpdateRequestDto.getDesc());
        movie.setScenaristId(movieUpdateRequestDto.getScenaristId());
        movie.setReleaseDate(movieUpdateRequestDto.getReleaseDate());
        movie.setImage(movieUpdateRequestDto.getImage());
        movie.setProducer(movieUpdateRequestDto.getProducer());
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(MovieDeleteRequestDto movieDeleteRequestDto) {
        Optional<Movie> movieEnt = movieRepository.findById(movieDeleteRequestDto.getMovieId());
        if (movieEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.MOVIE_NOT_FOUND));
        Movie movie = movieEnt.get();
        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> findByScenaristId(String scenaristId) {
        return movieRepository.findByScenaristId(scenaristId);
    }

    @Override
    public List<Movie> allMovie() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findByMovieList(List<String> movieIdList) {
        return movieRepository.findByMovieList(movieIdList);
    }

    @Override
    public MovieDetailResponseDto movieDetail(MovieDetailRequestDto movieDetailRequestDto) {
        Optional<Movie> movieEnt = movieRepository.findById(movieDetailRequestDto.getMovieId());
        if (movieEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.MOVIE_NOT_FOUND));
        Movie movie = movieEnt.get();

        List<ActorMovie> actorMovies = actorMovieService.movieActors(movie.getId().toString());
        List<String> movieActorsIdList = actorMovies.stream()
                .map(ActorMovie::getMovieId)
                .collect(Collectors.toList());

        List<CompanyMovie> movieCompany = companyMovieService.movieCompanies(movie.getId().toString());
        List<String> movieCompaniesIdList = movieCompany.stream()
                .map(CompanyMovie::getCompanyId)
                .collect(Collectors.toList());


        MovieDetailResponseDto movieDetailResponseDto = new MovieDetailResponseDto();
        movieDetailResponseDto.setMovie(movie);
        movieDetailResponseDto.setMovieActorsIds(movieActorsIdList);
        movieDetailResponseDto.setMovieCompanyIds(movieCompaniesIdList);


        return movieDetailResponseDto;
    }
}
