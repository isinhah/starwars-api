package com.api.swapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String director;
    private String producer;
    @Column(name = "episode_id")
    private Long episodeId;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private ZonedDateTime created;
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "characters", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "characters_url")
    private List<String> characters = new ArrayList<>();
}