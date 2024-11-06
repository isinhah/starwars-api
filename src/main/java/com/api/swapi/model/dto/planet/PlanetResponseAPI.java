package com.api.swapi.model.dto.planet;

import java.util.List;

public record PlanetResponseAPI(
        List<PlanetResponseDTO> results,
        String next) {
}
