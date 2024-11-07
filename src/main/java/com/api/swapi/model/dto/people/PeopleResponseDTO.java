package com.api.swapi.model.dto.people;

import com.api.swapi.model.People;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public record PeopleResponseDTO(
        Long id,
        String name,
        String gender,
        @JsonProperty("birth_year")
        String birthYear,
        String homeworld,
        ZonedDateTime created,
        ZonedDateTime edited,
        List<String> films
) {
    public PeopleResponseDTO(People people) {
        this(people.getId(), people.getName(), people.getGender(), people.getBirthYear(), people.getHomeworld(), people.getCreated(), people.getEdited(), people.getFilms());
    }
}
