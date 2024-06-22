package com.imdb.data;

import com.imdb.domain.entities.Actor;
import com.imdb.domain.entities.ActorMovie;
import com.imdb.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ActorMovieRepository extends JpaRepository<ActorMovie, UUID> {
    List<ActorMovie> findByActorId(String actorId);
    List<ActorMovie> findByMovieId(String movieId);

}
