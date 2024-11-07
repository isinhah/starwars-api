package com.api.swapi.controller.swagger;

import com.api.swapi.model.dto.film.FilmResponseDTO;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import com.api.swapi.model.dto.planet.PlanetResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "SWAPI Controller")
public interface SwapiControllerDoc {

    @Operation(summary = "Retrieve all planets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of planets retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    List<PlanetResponseDTO> getAllPlanetsFromSwapiAPI();

    @Operation(summary = "Retrieve planet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planet retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Planet not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<PlanetResponseDTO> getPlanetByIdFromSwapiAPI(
            @Parameter(description = "ID of the planet", required = true) Long id);

    @Operation(summary = "Search for planets by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planets retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<PlanetResponseDTO>> searchPlanetsFromSwapiAPI(
            @Parameter(description = "Name of the planet to search for", required = true) String name);

    @Operation(summary = "Retrieve all films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of films retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    List<FilmResponseDTO> getAllFilmsFromSwapiAPI();

    @Operation(summary = "Retrieve film by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Film not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<FilmResponseDTO> getFilmByIdFromSwapiAPI(
            @Parameter(description = "ID of the film", required = true) Long id);

    @Operation(summary = "Search for films by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Films retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<FilmResponseDTO>> searchFilmsFromSwapiAPI(
            @Parameter(description = "Title of the film to search for", required = true) String title);

    @Operation(summary = "Retrieve all people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of people retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    List<PeopleResponseDTO> getAllPeopleFromSwapiAPI();

    @Operation(summary = "Retrieve person by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<PeopleResponseDTO> getPeopleByIdFromSwapiAPI(
            @Parameter(description = "ID of the person", required = true) Long id);

    @Operation(summary = "Search for people by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "People retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<PeopleResponseDTO>> searchPeopleFromSwapiAPI(
            @Parameter(description = "Name of the person to search for", required = true) String name);
}