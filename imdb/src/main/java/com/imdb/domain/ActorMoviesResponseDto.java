package com.imdb.domain;

import lombok.Data;

import java.util.List;

@Data
public class ActorMoviesResponseDto {

    private String actorId;

    private String actorName;

    private List<String> actorMoviesIds;

}
