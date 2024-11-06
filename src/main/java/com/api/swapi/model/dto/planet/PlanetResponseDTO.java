package com.api.swapi.model.dto.planet;

import com.api.swapi.model.Planet;

import java.time.ZonedDateTime;
import java.util.List;

public record PlanetResponseDTO(
        Long id,
        String name,
        String terrain,
        String population,
        ZonedDateTime created,
        ZonedDateTime edited,
        List<String> residents,
        List<String> films
) {
    public PlanetResponseDTO(Planet planet) {
        this(planet.getId(), planet.getName(), planet.getTerrain(), planet.getPopulation(), planet.getCreated(), planet.getEdited(), planet.getResidents(), planet.getFilms());
    }
}