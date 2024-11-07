package com.api.swapi.controller;

import com.api.swapi.model.dto.people.PeopleRequestDTO;
import com.api.swapi.model.dto.people.PeopleResponseAPI;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import com.api.swapi.service.PeopleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final RestTemplate restTemplate;

    public PeopleController(PeopleService peopleService, RestTemplate restTemplate) {
        this.peopleService = peopleService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/swapi")
    public List<PeopleResponseDTO> getAllPeopleFromSwapiAPI() {
        String url = "https://swapi.dev/api/people";
        ResponseEntity<PeopleResponseAPI> response = restTemplate.getForEntity(url, PeopleResponseAPI.class);
        return response.getBody().results();
    }

    @GetMapping("/swapi/{id}")
    public ResponseEntity<PeopleResponseDTO> getPeopleByIdFromSwapiAPI(@PathVariable Long id) {
        String url = "https://swapi.dev/api/people/" + id;
        PeopleResponseDTO dto = restTemplate.getForObject(url, PeopleResponseDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/swapi/search")
    public ResponseEntity<List<PeopleResponseDTO>> searchPeopleFromSwapiAPI(@RequestParam String name) {
        String url = "https://swapi.dev/api/people?search=" + name;
        PeopleResponseAPI response = restTemplate.getForObject(url, PeopleResponseAPI.class);
        return ResponseEntity.ok(response.results());
    }

    @PostMapping("/populate")
    public ResponseEntity<String> populatePeople() {
        peopleService.savePeopleInDatabase();
        return ResponseEntity.ok("People's data has been saved in the database!");
    }

    @GetMapping
    public List<PeopleResponseDTO> getAllPeople(Pageable pageable) {
        return peopleService.getAllPeople(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponseDTO> getPersonById(@PathVariable Long id) {
        PeopleResponseDTO dto = peopleService.getPersonById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public List<PeopleResponseDTO> getAllPeopleByName(@RequestParam String name, Pageable pageable) {
        return peopleService.getAllPeopleByName(name, pageable).getContent();
    }

    @PostMapping
    public ResponseEntity<PeopleResponseDTO> createPlanet(@Validated @RequestBody PeopleRequestDTO dto) {
        PeopleResponseDTO createdPerson = peopleService.createPerson(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeopleResponseDTO> updatePerson(@PathVariable Long id, @Validated @RequestBody PeopleRequestDTO dto) {
        PeopleResponseDTO updatedPerson = peopleService.updatePerson(id, dto);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        peopleService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
