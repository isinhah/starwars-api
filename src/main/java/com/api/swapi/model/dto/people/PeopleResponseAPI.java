package com.api.swapi.model.dto.people;

import java.util.List;

public record PeopleResponseAPI(
        List<PeopleResponseDTO> results,
        String next) {
}