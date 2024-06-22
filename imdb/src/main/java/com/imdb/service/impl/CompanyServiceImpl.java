package com.imdb.service.impl;

import com.imdb.config.LocalizationResolver;
import com.imdb.data.CompanyRepository;
import com.imdb.domain.CompanyCreateRequestDto;
import com.imdb.domain.CompanyMoviesRequestDto;
import com.imdb.domain.CompanyMoviesResponseDto;
import com.imdb.domain.CompanyUpdateRequestDto;
import com.imdb.domain.dto.ImdbResultMessage;
import com.imdb.domain.entities.Company;
import com.imdb.domain.entities.CompanyMovie;
import com.imdb.service.CompanyMovieService;
import com.imdb.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMovieService companyMovieService;
    private final LocalizationResolver localizationResolver;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMovieService companyMovieService, LocalizationResolver localizationResolver) {
        this.companyRepository = companyRepository;
        this.companyMovieService = companyMovieService;
        this.localizationResolver = localizationResolver;
    }

    @Override
    public void createCompay(CompanyCreateRequestDto companyCreateRequestDto) {
        Company company = new Company();
        company.setCompanyName(companyCreateRequestDto.getCompanyName());
        company.setFoundationYear(companyCreateRequestDto.getFoundationYear());
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(CompanyUpdateRequestDto companyUpdateRequestDto) {
        Optional<Company> companyEnt = companyRepository.findById(companyUpdateRequestDto.getCompanyId());
        if (companyEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.COMPANY_NOT_FOUND));
        Company company = companyEnt.get();
        company.setCompanyName(companyUpdateRequestDto.getCompanyName());
        company.setFoundationYear(companyUpdateRequestDto.getFoundationYear());
        companyMovieService.createCompanyMovie(company.getId().toString(), companyUpdateRequestDto.getMovieId());
        companyRepository.save(company);
    }


    @Override
    public List<Company> allCompany() {
        return companyRepository.findAll();
    }

    @Override
    public CompanyMoviesResponseDto companyMovies(CompanyMoviesRequestDto companyMoviesRequestDto) {

        Optional<Company> companyEnt = companyRepository.findById(companyMoviesRequestDto.getCompnayId());
        if (companyEnt.isEmpty())
            throw new IllegalArgumentException(localizationResolver.resolve(ImdbResultMessage.COMPANY_NOT_FOUND));
        Company company = companyEnt.get();
        List<CompanyMovie> companyMovies = companyMovieService.companyMovies(company.getId().toString());
        List<String> moviesIdList = companyMovies.stream()
                .map(CompanyMovie::getMovieId)
                .collect(Collectors.toList());
        CompanyMoviesResponseDto companyMoviesResponseDto = new CompanyMoviesResponseDto();
        companyMoviesResponseDto.setCompanyId(company.getId().toString());
        companyMoviesResponseDto.setCompanyName(company.getCompanyName());
        companyMoviesResponseDto.setCompanyMoviesIds(moviesIdList);

        return companyMoviesResponseDto;
    }
}
