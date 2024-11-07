package com.api.swapi.model;

import com.api.swapi.model.dto.people.PeopleRequestDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String gender;
    @Column(name = "birth_year")
    private String birthYear;
    private String homeworld;
    private ZonedDateTime created;
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "people_films")
    @Column(name = "films_url")
    private List<String> films = new ArrayList<>();

    public void alterPerson(PeopleRequestDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.gender() != null) {
            this.gender = dto.gender();
        }
        if (dto.birthYear() != null) {
            this.birthYear = dto.birthYear();
        }
        if (dto.homeworld() != null) {
            this.homeworld = dto.homeworld();
        }
        if (dto.films() != null) {
            this.films = dto.films();
        }
        this.edited = ZonedDateTime.now();
    }
}