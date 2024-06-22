package com.imdb.data;

import com.imdb.domain.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Optional<Movie> findById(UUID id);

    List<Movie> findByScenaristId(String scenaristId);

    @Query(value = "select m from Movie m where m.id IN :idList",nativeQuery = true)
    List<Movie> findByMovieList(@Param("idList") List<String> idList);
}
