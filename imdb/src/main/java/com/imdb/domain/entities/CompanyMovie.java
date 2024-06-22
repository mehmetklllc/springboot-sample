package com.imdb.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "company-movie")
@Data
public class CompanyMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String companyId;
    private String movieId;
}
