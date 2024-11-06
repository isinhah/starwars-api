package com.api.swapi.model;

import com.api.swapi.model.dto.planet.PlanetRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String terrain;
    private String population;
    @CreationTimestamp
    private ZonedDateTime created;
    @UpdateTimestamp
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "planets_residents")
    @Column(name = "residents_url")
    private List<String> residents = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "planets_films")
    @Column(name = "films_url")
    private List<String> films = new ArrayList<>();

    public void alterPlanet(PlanetRequestDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.terrain() != null) {
            this.terrain = dto.terrain();
        }
        if (dto.population() != null) {
            this.population = dto.population();
        }
        if (dto.residents() != null) {
            this.residents = dto.residents();
        }
        if (dto.films() != null) {
            this.films = dto.films();
        }
        this.edited = ZonedDateTime.now();
     }
}