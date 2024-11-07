package com.api.swapi.controller.swagger;

import com.api.swapi.model.dto.film.FilmRequestDTO;
import com.api.swapi.model.dto.film.FilmResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Film Controller")
public interface FilmControllerDoc {

    @Operation(summary = "Populate films' data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Films' data saved successfully")
    })
    ResponseEntity<String> populateFilms();

    @Operation(summary = "Get all films")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of all films retrieved successfully")
    })
    List<FilmResponseDTO> getAllFilms(
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Get film by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Film not found")
    })
    ResponseEntity<FilmResponseDTO> getFilmById(
            @Parameter(description = "ID of the film to be retrieved") Long id);

    @Operation(summary = "Search films by title")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of films retrieved successfully")
    })
    List<FilmResponseDTO> getAllFilmsByTitle(
            @Parameter(description = "Title of the film to search for") String title,
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Create new film")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Film created successfully")
    })
    ResponseEntity<FilmResponseDTO> createFilm(
            @Parameter(description = "Film data to create") FilmRequestDTO dto);

    @Operation(summary = "Update an existing film")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film updated successfully"),
            @ApiResponse(responseCode = "404", description = "Film not found")
    })
    ResponseEntity<FilmResponseDTO> updateFilm(
            @Parameter(description = "ID of the film to be updated") Long id,
            @Parameter(description = "Updated film data") FilmRequestDTO dto);

    @Operation(summary = "Delete a film")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Film deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Film not found")
    })
    ResponseEntity<Void> deleteFilm(
            @Parameter(description = "ID of the film to be deleted") Long id);
}
