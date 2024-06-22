package com.imdb.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class CompanyUpdateRequestDto {

    private UUID companyId;
    private String companyName;
    private String foundationYear;
    private String movieId;

}
