package com.api.swapi.service;

import com.api.swapi.exceptions.ConflictException;
import com.api.swapi.model.Planet;
import com.api.swapi.model.dto.PlanetRequestDTO;
import com.api.swapi.model.dto.PlanetResponseAPI;
import com.api.swapi.model.dto.PlanetResponseDTO;
import com.api.swapi.repository.PlanetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
        ResponseEntity<PlanetResponseAPI> response = restTemplate.getForEntity(url, PlanetResponseAPI.class);

        List<PlanetResponseDTO> planets = response.getBody().results();
        for (PlanetResponseDTO dto : planets) {
            Planet planet = Planet.builder()
                    .id(dto.id())
                    .name(dto.name())
                    .terrain(dto.terrain())
                    .population(dto.population())
                    .created(ZonedDateTime.now())
                    .residents(dto.residents())
                    .films(dto.films())
                    .build();

            planetRepository.save(planet);
        }
    }

    @Transactional
    public PlanetResponseDTO createPlanet(PlanetRequestDTO dto) {
        if (planetRepository.existsByName(dto.name())) {
            throw new ConflictException("Name already exists");
        }

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
        Planet planet = planetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planet not found with this id"));

        planet.alterPlanet(dto);

        planetRepository.save(planet);
        return new PlanetResponseDTO(planet);
    }

    @Transactional
    public void deletePlanet(Long id) {
        planetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Planet not found with this id"));
        planetRepository.deleteById(id);
    }
}