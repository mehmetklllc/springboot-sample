package com.imdb.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "actor-movie")
@Data
public class ActorMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String actorId;
    private String movieId;
}
