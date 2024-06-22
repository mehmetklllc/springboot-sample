package com.imdb.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity(name = "movie")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String movieName;
    private String releaseDate;
    private String desc;
    private String producer;
    private String budget;
    private String image;
    private String scenaristId;

}
