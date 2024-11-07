package com.api.swapi.model.dto.film;

import com.api.swapi.model.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record FilmResponseDTO(
        Long id,
        String title,
        String director,
        String producer,
        @JsonProperty("episode_id")
        Long episodeId,
        @JsonProperty("release_date")
        LocalDate releaseDate,
        ZonedDateTime created,
        ZonedDateTime edited,
        List<String> characters
) {
    public FilmResponseDTO(Film film) {
        this(film.getId(), film.getTitle(), film.getDirector(), film.getProducer(), film.getEpisodeId(), film.getReleaseDate(), film.getCreated(), film.getEdited(), film.getCharacters());
    }
}
