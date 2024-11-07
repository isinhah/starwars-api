package com.api.swapi.model.dto.people;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record PeopleRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,
        @NotBlank(message = "O gênero é obrigatório")
        @Pattern(regexp = "male|female|hermaphrodite|n/a", message = "Gênero deve ser 'male', 'female', 'hermaphrodite' ou 'n/a'")
        String gender,
        String birthYear,
        String homeworld,
        List<String> films
) {
}
