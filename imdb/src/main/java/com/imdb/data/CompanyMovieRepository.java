package com.imdb.data;

import com.imdb.domain.entities.ActorMovie;
import com.imdb.domain.entities.CompanyMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CompanyMovieRepository extends JpaRepository<CompanyMovie, UUID> {
    List<CompanyMovie> findByCompanyId(String companyId);
    List<CompanyMovie> findByMovieId(String movieId);

}
