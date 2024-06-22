package com.imdb.service.impl;

import com.imdb.data.CompanyMovieRepository;
import com.imdb.domain.entities.ActorMovie;
import com.imdb.domain.entities.CompanyMovie;
import com.imdb.service.ActorMovieService;
import com.imdb.service.CompanyMovieService;
import com.imdb.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyMovieServiceImpl implements CompanyMovieService {

    private final CompanyMovieRepository companyMovieRepository;

    public CompanyMovieServiceImpl(CompanyMovieRepository companyMovieRepository) {
        this.companyMovieRepository = companyMovieRepository;
    }


    @Override
    public void createCompanyMovie(String companyId, String movieId) {
        CompanyMovie companyMovie = new CompanyMovie();
        companyMovie.setCompanyId(companyId);
        companyMovie.setMovieId(movieId);
        companyMovieRepository.save(companyMovie);
    }

    @Override
    public List<CompanyMovie> companyMovies(String companyId) {
        return companyMovieRepository.findByCompanyId(companyId);
    }

    @Override
    public List<CompanyMovie> movieCompanies(String movieId) {
        return companyMovieRepository.findByMovieId(movieId);
    }

}
