package com.imdb.service;

import com.imdb.domain.entities.ActorMovie;
import com.imdb.domain.entities.CompanyMovie;

import java.util.List;

public interface CompanyMovieService {

    void createCompanyMovie(String companyId,String movieId);

    List<CompanyMovie> companyMovies(String companyId);

    List<CompanyMovie> movieCompanies(String movieId);
}
