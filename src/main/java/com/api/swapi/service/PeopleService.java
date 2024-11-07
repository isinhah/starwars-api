package com.api.swapi.service;

import com.api.swapi.model.People;
import com.api.swapi.model.dto.people.PeopleRequestDTO;
import com.api.swapi.model.dto.people.PeopleResponseAPI;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import com.api.swapi.repository.PeopleRepository;
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
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final RestTemplate restTemplate;

    public PeopleService(PeopleRepository peopleRepository, RestTemplate restTemplate) {
        this.peopleRepository = peopleRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void savePeopleInDatabase() {
        String url = "https://swapi.dev/api/people";

        while (url != null) {
            ResponseEntity<PeopleResponseAPI> response = restTemplate.getForEntity(url, PeopleResponseAPI.class);
            List<PeopleResponseDTO> people = response.getBody().results();
            url = response.getBody().next();

            for (PeopleResponseDTO dto : people) {
                People p = People.builder()
                        .id(dto.id())
                        .name(dto.name())
                        .gender(dto.gender())
                        .birthYear(dto.birthYear())
                        .homeworld(dto.homeworld())
                        .created(ZonedDateTime.now())
                        .films(dto.films())
                        .build();
                peopleRepository.save(p);
            }
        }
    }

    public Page<PeopleResponseDTO> getAllPeople(Pageable pageable) {
        Page<People> people = peopleRepository.findAll(pageable);
        return people.map(PeopleResponseDTO::new);
    }

    public PeopleResponseDTO getPersonById(Long id) {
        People person = verifyPersonExistsById(id);
        return new PeopleResponseDTO(person);
    }

    public Page<PeopleResponseDTO> getAllPeopleByName(String name, Pageable pageable) {
        Page<People> people = peopleRepository.findByNameContainingIgnoreCase(name, pageable);
        return people.map(PeopleResponseDTO::new);
    }

    @Transactional
    public PeopleResponseDTO createPerson(PeopleRequestDTO dto) {
        People person = People.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birthYear(dto.birthYear())
                .homeworld(dto.homeworld())
                .created(ZonedDateTime.now())
                .films(dto.films())
                .build();

        peopleRepository.save(person);
        return new PeopleResponseDTO(person);
    }

    @Transactional
    public PeopleResponseDTO updatePerson(Long id, PeopleRequestDTO dto) {
        People person = verifyPersonExistsById(id);

        person.alterPerson(dto);

        peopleRepository.save(person);
        return new PeopleResponseDTO(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        verifyPersonExistsById(id);
        peopleRepository.deleteById(id);
    }

    private People verifyPersonExistsById(Long id) {
        return peopleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with this id."));
    }
}