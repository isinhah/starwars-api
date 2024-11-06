package com.api.swapi.model.dto.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record FilmRequestDTO(
        @NotBlank(message = "O titulo é obrigatório")
        String title,
        String director,
        String producer,
        Long episodeId,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate releaseDate,
        @Schema(description = "Lista de URLs dos personagens do filme (formato: http://swapi.dev/api/people/1)")
        List<String> characters
) {
}