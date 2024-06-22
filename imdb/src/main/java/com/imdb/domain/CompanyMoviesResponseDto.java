package com.imdb.domain;

import lombok.Data;

import java.util.List;

@Data
public class CompanyMoviesResponseDto {

    private String companyId;

    private String companyName;

    private List<String> companyMoviesIds;
}
