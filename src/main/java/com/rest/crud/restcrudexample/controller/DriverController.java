package com.rest.crud.restcrudexample.controller;

import com.rest.crud.restcrudexample.model.Driver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Tag(name = "Driver", description = "The Driver API")
public interface DriverController {

    @Operation(
            summary = "Fetch all drivers",
            description = "Fetches all drivers entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<List<Driver>> findAll();

    @Operation(
            summary = "Fetch driver by ID",
            description = "Fetches driver entity and its data from data source by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Optional<Driver>> findById(Long id);

    @Operation(
            summary = "Save driver",
            description = "Saves driver entity to data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created / Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Driver> save(Driver driver);

    @Operation(
            summary = "Update driver",
            description = "Updates driver entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Driver> update(Long id, Driver driver);

    @Operation(
            summary = "Update driver partial",
            description = "Updates driver entity partial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Driver> updatePartial(Long id, Driver driver);

    @Operation(
            summary = "Delete driver by ID",
            description = "Deletes driver entity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    ResponseEntity<Void> deleteById(Long id);
}
