package com.api.swapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "birth_year")
    private String birthYear;
    private String homeworld;
    private ZonedDateTime created;
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "people_films")
    @Column(name = "films_url")
    private List<String> films = new ArrayList<>();

    public enum Gender {
        MALE,
        FEMALE
    }
}