package com.imdb.domain;

import lombok.Data;

@Data
public class CompanyCreateRequestDto {
    private String companyName;
    private String foundationYear;
}
