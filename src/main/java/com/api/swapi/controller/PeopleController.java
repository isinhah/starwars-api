package com.api.swapi.controller;

import com.api.swapi.model.dto.people.PeopleRequestDTO;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import com.api.swapi.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

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
