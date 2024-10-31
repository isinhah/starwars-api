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
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String terrain;
    private String population;
    private ZonedDateTime created;
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "residents", joinColumns = @JoinColumn(name = "planet_id"))
    @Column(name = "residents_url")
    private List<String> residents = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "films", joinColumns = @JoinColumn(name = "planet_id"))
    @Column(name = "films_url")
    private List<String> films = new ArrayList<>();
}