package com.api.swapi.controller.swagger;

import com.api.swapi.model.dto.planet.PlanetRequestDTO;
import com.api.swapi.model.dto.planet.PlanetResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Planet Controller")
public interface PlanetControllerDoc {

    @Operation(summary = "Populate planets")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planets data saved successfully")
    })
    ResponseEntity<String> populatePlanets();

    @Operation(summary = "Get all planets")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of all planets retrieved successfully")
    })
    List<PlanetResponseDTO> getAllPlanets(
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Get planet by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planet retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Planet not found")
    })
    ResponseEntity<PlanetResponseDTO> getPlanetById(
            @Parameter(description = "ID of the planet to be retrieved") Long id);

    @Operation(summary = "Search planets by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of planets retrieved successfully")
    })
    List<PlanetResponseDTO> getAllPlanetsByName(
            @Parameter(description = "Name of the planet to search for") String name,
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Create new planet")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Planet created successfully")
    })
    ResponseEntity<PlanetResponseDTO> createPlanet(
            @Parameter(description = "Planet data to create") PlanetRequestDTO dto);

    @Operation(summary = "Update an existing planet")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planet updated successfully"),
            @ApiResponse(responseCode = "404", description = "Planet not found")
    })
    ResponseEntity<PlanetResponseDTO> updatePlanet(
            @Parameter(description = "ID of the planet to be updated") Long id,
            @Parameter(description = "Updated planet data") PlanetRequestDTO dto);

    @Operation(summary = "Delete a planet")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Planet deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Planet not found")
    })
    ResponseEntity<Void> deletePlanet(
            @Parameter(description = "ID of the planet to be deleted") Long id);
}
