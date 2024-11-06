package com.api.swapi.repository;

import com.api.swapi.model.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Page<Planet> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
