package com.api.swapi.controller;

import com.api.swapi.model.dto.PlanetRequestDTO;
import com.api.swapi.model.dto.PlanetResponseAPI;
import com.api.swapi.model.dto.PlanetResponseDTO;
import com.api.swapi.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planets")
public class PlanetController {

    private final PlanetService planetService;
    private final RestTemplate restTemplate;

    public PlanetController(PlanetService planetService, RestTemplate restTemplate) {
        this.planetService = planetService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<PlanetResponseDTO> getAllPlanetsFromSwapiAPI() {
        String url = "https://swapi.dev/api/planets";
        ResponseEntity<PlanetResponseAPI> response = restTemplate.getForEntity(url, PlanetResponseAPI.class);
        return response.getBody().results();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetResponseDTO> getPlanetByIdFromSwapiAPI(@PathVariable Long id) {
        String url = "https://swapi.dev/api/planets/" + id;
        PlanetResponseDTO dto = restTemplate.getForObject(url, PlanetResponseDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PlanetResponseDTO>> searchPlanetsFromSwapiAPI(@RequestParam String name) {
        String url = "https://swapi.dev/api/planets?search=" + name;
        PlanetResponseAPI response = restTemplate.getForObject(url, PlanetResponseAPI.class);
        return ResponseEntity.ok(response.results());
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populatePlanets() {
        planetService.savePlanetsInDatabase();
        return ResponseEntity.ok("The planets data have been saved in the database!");
    }

    @PostMapping
    public ResponseEntity<PlanetResponseDTO> createPlanet(@Validated @RequestBody PlanetRequestDTO dto) {
        PlanetResponseDTO createdPlanet = planetService.createPlanet(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetResponseDTO> updatePlanet(@PathVariable Long id, @Validated @RequestBody PlanetRequestDTO dto) {
        PlanetResponseDTO updatedPlanet = planetService.updatePlanet(id, dto);
        return ResponseEntity.ok(updatedPlanet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}