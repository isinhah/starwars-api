package com.api.swapi.model.dto.film;

import com.api.swapi.model.Film;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record FilmResponseDTO(
        Long id,
        String title,
        String director,
        String producer,
        Long episodeId,
        LocalDate releaseDate,
        ZonedDateTime created,
        ZonedDateTime edited,
        List<String> characters
) {
    public FilmResponseDTO(Film film) {
        this(film.getId(), film.getTitle(), film.getDirector(), film.getProducer(), film.getEpisodeId(), film.getReleaseDate(), film.getCreated(), film.getEdited(), film.getCharacters());
    }
}
