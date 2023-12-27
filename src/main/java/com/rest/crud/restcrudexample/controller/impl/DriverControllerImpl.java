package com.rest.crud.restcrudexample.controller.impl;

import com.rest.crud.restcrudexample.controller.DriverController;
import com.rest.crud.restcrudexample.model.Driver;
import com.rest.crud.restcrudexample.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/drivers")
@RequiredArgsConstructor
public class DriverControllerImpl implements DriverController {

    private final DriverService driverService;

    @GetMapping()
    public ResponseEntity<List<Driver>> findAll() {
        var all = driverService.findAll();
        return all.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Driver>> findById(@PathVariable("id") Long id) {
        var byId = driverService.findById(id);
        return byId.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(byId);
    }

    @PostMapping()
    public ResponseEntity<Driver> save(@Valid @RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.save(driver), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(@PathVariable("id") Long id, @Valid @RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.update(id, driver));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Driver> updatePartial(@PathVariable("id") Long id, @Valid @RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.updatePartial(id, driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        driverService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
