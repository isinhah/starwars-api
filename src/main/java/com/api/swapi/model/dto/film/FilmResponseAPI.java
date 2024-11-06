package com.api.swapi.model.dto.film;

import java.util.List;

public record FilmResponseAPI(
        List<FilmResponseDTO> results,
        String next) {
}
