package com.api.swapi.model;

import com.api.swapi.model.dto.film.FilmRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
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
    @CreationTimestamp
    private ZonedDateTime created;
    @UpdateTimestamp
    private ZonedDateTime edited;

    @ElementCollection
    @CollectionTable(name = "films_characters")
    @Column(name = "characters_url")
    private List<String> characters = new ArrayList<>();

    public void alterFilm(FilmRequestDTO dto) {
        if (dto.title() != null) {
            this.title = dto.title();
        }
        if (dto.director() != null) {
            this.director = dto.director();
        }
        if (dto.producer() != null) {
            this.producer = dto.producer();
        }
        if (dto.episodeId() != null) {
            this.episodeId = dto.episodeId();
        }
        if (dto.releaseDate() != null) {
            this.releaseDate = dto.releaseDate();
        }
        if (dto.characters() != null) {
            this.characters = dto.characters();
        }

        this.edited = ZonedDateTime.now();
    }
}