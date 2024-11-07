package com.api.swapi.controller;

import com.api.swapi.model.dto.film.FilmResponseAPI;
import com.api.swapi.model.dto.film.FilmResponseDTO;
import com.api.swapi.model.dto.people.PeopleResponseAPI;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import com.api.swapi.model.dto.planet.PlanetResponseAPI;
import com.api.swapi.model.dto.planet.PlanetResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/swapi")
public class SwapiController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/planets")
    public List<PlanetResponseDTO> getAllPlanetsFromSwapiAPI() {
        String url = "https://swapi.dev/api/planets";
        ResponseEntity<PlanetResponseAPI> response = restTemplate.getForEntity(url, PlanetResponseAPI.class);
        return response.getBody().results();
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<PlanetResponseDTO> getPlanetByIdFromSwapiAPI(@PathVariable Long id) {
        String url = "https://swapi.dev/api/planets/" + id;
        PlanetResponseDTO dto = restTemplate.getForObject(url, PlanetResponseDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/planets/search")
    public ResponseEntity<List<PlanetResponseDTO>> searchPlanetsFromSwapiAPI(@RequestParam String name) {
        String url = "https://swapi.dev/api/planets?search=" + name;
        PlanetResponseAPI response = restTemplate.getForObject(url, PlanetResponseAPI.class);
        return ResponseEntity.ok(response.results());
    }

    @GetMapping("/films")
    public List<FilmResponseDTO> getAllFilmsFromSwapiAPI() {
        String url = "https://swapi.dev/api/films";
        ResponseEntity<FilmResponseAPI> response = restTemplate.getForEntity(url, FilmResponseAPI.class);
        return response.getBody().results();
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmByIdFromSwapiAPI(@PathVariable Long id) {
        String url = "https://swapi.dev/api/films/" + id;
        FilmResponseDTO dto = restTemplate.getForObject(url, FilmResponseDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/films/search")
    public ResponseEntity<List<FilmResponseDTO>> searchFilmsFromSwapiAPI(@RequestParam String title) {
        String url = "https://swapi.dev/api/films?search=" + title;
        FilmResponseAPI response = restTemplate.getForObject(url, FilmResponseAPI.class);
        return ResponseEntity.ok(response.results());
    }

    @GetMapping("/people")
    public List<PeopleResponseDTO> getAllPeopleFromSwapiAPI() {
        String url = "https://swapi.dev/api/people";
        ResponseEntity<PeopleResponseAPI> response = restTemplate.getForEntity(url, PeopleResponseAPI.class);
        return response.getBody().results();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PeopleResponseDTO> getPeopleByIdFromSwapiAPI(@PathVariable Long id) {
        String url = "https://swapi.dev/api/people/" + id;
        PeopleResponseDTO dto = restTemplate.getForObject(url, PeopleResponseDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/people/search")
    public ResponseEntity<List<PeopleResponseDTO>> searchPeopleFromSwapiAPI(@RequestParam String name) {
        String url = "https://swapi.dev/api/people?search=" + name;
        PeopleResponseAPI response = restTemplate.getForObject(url, PeopleResponseAPI.class);
        return ResponseEntity.ok(response.results());
    }
}
