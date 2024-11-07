package com.api.swapi.controller;

import com.api.swapi.controller.swagger.PlanetControllerDoc;
import com.api.swapi.model.dto.planet.PlanetRequestDTO;
import com.api.swapi.model.dto.planet.PlanetResponseDTO;
import com.api.swapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planets")
public class PlanetController implements PlanetControllerDoc {

    @Autowired
    private PlanetService planetService;

    @PostMapping("/populate")
    public ResponseEntity<String> populatePlanets() {
        planetService.savePlanetsInDatabase();
        return ResponseEntity.ok("The planets data have been saved in the database!");
    }

    @GetMapping
    public List<PlanetResponseDTO> getAllPlanets(Pageable pageable) {
        return planetService.getAllPlanets(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetResponseDTO> getPlanetById(@PathVariable Long id) {
        PlanetResponseDTO dto = planetService.getPlanetById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public List<PlanetResponseDTO> getAllPlanetsByName(@RequestParam String name, Pageable pageable) {
        return planetService.getAllPlanetsByName(name, pageable).getContent();
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