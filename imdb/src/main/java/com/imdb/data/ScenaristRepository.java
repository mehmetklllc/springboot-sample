package com.imdb.data;

import com.imdb.domain.entities.Scenarist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScenaristRepository extends JpaRepository<Scenarist, UUID> {
    Optional<Scenarist> findById(UUID id);
}
