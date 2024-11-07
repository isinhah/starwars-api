package com.api.swapi.service;

import com.api.swapi.model.Film;
import com.api.swapi.model.dto.film.FilmRequestDTO;
import com.api.swapi.model.dto.film.FilmResponseAPI;
import com.api.swapi.model.dto.film.FilmResponseDTO;
import com.api.swapi.repository.FilmRepository;
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
public class FilmService {

    private final FilmRepository filmRepository;
    private final RestTemplate restTemplate;

    public FilmService(FilmRepository filmRepository, RestTemplate restTemplate) {
        this.filmRepository = filmRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void saveFilmsInDatabase() {
        String url = "https://swapi.dev/api/films";

        ResponseEntity<FilmResponseAPI> response = restTemplate.getForEntity(url, FilmResponseAPI.class);
        List<FilmResponseDTO> films = response.getBody().results();

        for (FilmResponseDTO dto : films) {
            Film film = Film.builder()
                    .id(dto.id())
                    .title(dto.title())
                    .director(dto.director())
                    .producer(dto.producer())
                    .episodeId(dto.episodeId())
                    .releaseDate(dto.releaseDate())
                    .characters(dto.characters())
                    .build();
            filmRepository.save(film);
        }
    }

    public Page<FilmResponseDTO> getAllFilms(Pageable pageable) {
        Page<Film> films = filmRepository.findAll(pageable);
        return films.map(FilmResponseDTO::new);
    }

    public FilmResponseDTO getFilmById(Long id) {
        Film film = verifyFilmIdExists(id);
        return new FilmResponseDTO(film);
    }

    public Page<FilmResponseDTO> getAllFilmsByTitle(String title, Pageable pageable) {
        Page<Film> films = filmRepository.findByTitleContainingIgnoreCase(title, pageable);
        return films.map(FilmResponseDTO::new);
    }

    @Transactional
    public FilmResponseDTO createFilm(FilmRequestDTO dto) {
        Film film = Film.builder()
                .title(dto.title())
                .director(dto.director())
                .producer(dto.producer())
                .episodeId(dto.episodeId())
                .releaseDate(dto.releaseDate())
                .created(ZonedDateTime.now())
                .characters(dto.characters())
                .build();

        filmRepository.save(film);
        return new FilmResponseDTO(film);
    }

    @Transactional
    public FilmResponseDTO updateFilm(Long id, FilmRequestDTO dto) {
        Film film = verifyFilmIdExists(id);

        film.alterFilm(dto);

        filmRepository.save(film);
        return new FilmResponseDTO(film);
    }

    @Transactional
    public void deleteFilm(Long id) {
        verifyFilmIdExists(id);
        filmRepository.deleteById(id);
    }

    private Film verifyFilmIdExists(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Film not found with this id"));
    }
}
