package com.api.swapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PlanetRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O tipo de terreno do planeta é obrigatório")
        String terrain,
        @Min(value = 0, message = "A população não pode ser negativa")
        String population,
        @Schema(description = "Lista de URLs dos residentes do planeta (formato: http://example.com/residents/1)")
        List<String> residents,
        @Schema(description = "Lista de URLs dos filmes em que o planeta aparece (formato: http://example.com/films/1)")
        List<String> films
) {
}