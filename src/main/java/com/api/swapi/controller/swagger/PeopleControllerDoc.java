package com.api.swapi.controller.swagger;

import com.api.swapi.model.dto.people.PeopleRequestDTO;
import com.api.swapi.model.dto.people.PeopleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "People Controller")
public interface PeopleControllerDoc {

    @Operation(summary = "Populate people's data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "People's data saved successfully")
    })
    ResponseEntity<String> populatePeople();

    @Operation(summary = "Get all people")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of all people retrieved successfully")
    })
    List<PeopleResponseDTO> getAllPeople(
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Get person by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    ResponseEntity<PeopleResponseDTO> getPersonById(
            @Parameter(description = "ID of the person to be retrieved") Long id);

    @Operation(summary = "Search people by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of people retrieved successfully")
    })
    List<PeopleResponseDTO> getAllPeopleByName(
            @Parameter(description = "Name of the person to search for") String name,
            @Parameter(description = "Pagination details") Pageable pageable);

    @Operation(summary = "Create new person")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Person created successfully")
    })
    ResponseEntity<PeopleResponseDTO> createPlanet(
            @Parameter(description = "Person data to create") PeopleRequestDTO dto);

    @Operation(summary = "Update an existing person")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person updated successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    ResponseEntity<PeopleResponseDTO> updatePerson(
            @Parameter(description = "ID of the person to be updated") Long id,
            @Parameter(description = "Updated person data") PeopleRequestDTO dto);

    @Operation(summary = "Delete a person")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Person deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    ResponseEntity<Void> deletePerson(
            @Parameter(description = "ID of the person to be deleted") Long id);
}
