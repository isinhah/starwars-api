package com.api.swapi.service;

import com.api.swapi.model.Planet;
import com.api.swapi.model.dto.planet.PlanetRequestDTO;
import com.api.swapi.model.dto.planet.PlanetResponseAPI;
import com.api.swapi.model.dto.planet.PlanetResponseDTO;
import com.api.swapi.repository.PlanetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final RestTemplate restTemplate;

    public PlanetService(PlanetRepository planetRepository, RestTemplate restTemplate) {
        this.planetRepository = planetRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void savePlanetsInDatabase() {
        String url = "https://swapi.dev/api/planets";

        while (url != null) {
            ResponseEntity<PlanetResponseAPI> response = restTemplate.getForEntity(url, PlanetResponseAPI.class);
            List<PlanetResponseDTO> planets = response.getBody().results();
            url = response.getBody().next();

            for (PlanetResponseDTO dto : planets) {
                Planet planet = Planet.builder()
                        .id(dto.id())
                        .name(dto.name())
                        .terrain(dto.terrain())
                        .population(dto.population())
                        .residents(dto.residents())
                        .films(dto.films())
                        .build();
                planetRepository.save(planet);
            }
        }
    }

    public Page<PlanetResponseDTO> getAllPlanets(Pageable pageable) {
        Page<Planet> planets = planetRepository.findAll(pageable);
        return planets.map(PlanetResponseDTO::new);
    }

    public PlanetResponseDTO getPlanetById(Long id) {
        Planet planet = verifyPlanetIdExists(id);
        return new PlanetResponseDTO(planet);
    }

    public Page<PlanetResponseDTO> getAllPlanetsByName(String name, Pageable pageable) {
        Page<Planet> planets = planetRepository.findByNameContainingIgnoreCase(name, pageable);
        return planets.map(PlanetResponseDTO::new);
    }

    @Transactional
    public PlanetResponseDTO createPlanet(PlanetRequestDTO dto) {
        Planet planet = Planet.builder()
                .name(dto.name())
                .terrain(dto.terrain())
                .population(dto.population())
                .created(ZonedDateTime.now())
                .residents(dto.residents())
                .films(dto.films())
                .build();

        planetRepository.save(planet);
        return new PlanetResponseDTO(planet);
    }

    @Transactional
    public PlanetResponseDTO updatePlanet(Long id, PlanetRequestDTO dto) {
        Planet planet = verifyPlanetIdExists(id);

        planet.alterPlanet(dto);

        planetRepository.save(planet);
        return new PlanetResponseDTO(planet);
    }

    @Transactional
    public void deletePlanet(Long id) {
        verifyPlanetIdExists(id);
        planetRepository.deleteById(id);
    }

    private Planet verifyPlanetIdExists(Long id) {
        return planetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planet not found with this id"));
    }
}