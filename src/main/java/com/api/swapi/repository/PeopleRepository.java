package com.api.swapi.repository;

import com.api.swapi.model.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    Page<People> findByNameContainingIgnoreCase(String name, Pageable pageable);
}