package com.api.swapi.controller;

import com.api.swapi.controller.swagger.FilmControllerDoc;
import com.api.swapi.model.dto.film.FilmRequestDTO;
import com.api.swapi.model.dto.film.FilmResponseDTO;
import com.api.swapi.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController implements FilmControllerDoc {

    @Autowired
    private FilmService filmService;

    @PostMapping("/populate")
    public ResponseEntity<String> populateFilms() {
        filmService.saveFilmsInDatabase();
        return ResponseEntity.ok("The films data have been saved in the database!");
    }

    @GetMapping
    public List<FilmResponseDTO> getAllFilms(Pageable pageable) {
        return filmService.getAllFilms(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable Long id) {
        FilmResponseDTO dto = filmService.getFilmById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public List<FilmResponseDTO> getAllFilmsByTitle(@RequestParam String title, Pageable pageable) {
        return filmService.getAllFilmsByTitle(title, pageable).getContent();
    }

    @PostMapping
    public ResponseEntity<FilmResponseDTO> createFilm(@Validated @RequestBody FilmRequestDTO dto) {
        FilmResponseDTO createdFilm = filmService.createFilm(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> updateFilm(@PathVariable Long id, @Validated @RequestBody FilmRequestDTO dto) {
        FilmResponseDTO updatedFilm = filmService.updateFilm(id, dto);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}