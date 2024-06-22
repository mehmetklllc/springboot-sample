package com.imdb.service;

import com.imdb.domain.*;
import com.imdb.domain.entities.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyService {

    void createCompay(CompanyCreateRequestDto companyCreateRequestDto);

    void updateCompany(CompanyUpdateRequestDto companyUpdateRequestDto);

    List<Company> allCompany();

    CompanyMoviesResponseDto companyMovies(CompanyMoviesRequestDto companyMoviesRequestDto);
}
