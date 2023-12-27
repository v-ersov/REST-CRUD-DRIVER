package com.rest.crud.restcrudexample.service.impl;

import com.rest.crud.restcrudexample.exception.DriverNotFoundException;
import com.rest.crud.restcrudexample.model.Driver;
import com.rest.crud.restcrudexample.repository.DriverRepository;
import com.rest.crud.restcrudexample.service.DriverService;
import com.rest.crud.restcrudexample.util.Patcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> findAll() {
        log.info("Find All Drivers");
        return driverRepository.findAll();
    }

    public Optional<Driver> findById(Long id) {
        log.info("Find Driver by ID: {}", id);
        Assert.notNull(id, "ID must be provided");
        return driverRepository.findById(id);
    }

    @Override
    public Driver save(Driver driver) {
        log.info("Save Driver: {}", driver);
        Assert.notNull(driver, "Driver must be provided");
        if (nonNull(driver.getId())) {
            driver.setId(null);
        }
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Long id, Driver driver) {
        log.info("Update Driver: {}; by ID: {}", driver, id);
        Assert.notNull(id, "Driver ID must be provided");
        Assert.notNull(driver, "Driver must be provided");
        Assert.isTrue(id.equals(driver.getId()), "Driver ID in path must be equals ID in Driver request body");

        var byId = findById(id);
        if (byId.isEmpty()) {
            throw new DriverNotFoundException(id);
        }

        return driverRepository.save(driver);
    }

    @Override
    public Driver updatePartial(Long id, Driver driver) {
        log.info("Update Partial Driver: {}; by ID: {}", driver, id);
        Assert.notNull(id, "Driver ID must be provided");
        Assert.notNull(driver, "Driver must be provided");

        var byId = findById(id);
        if (byId.isEmpty()) {
            throw new DriverNotFoundException(id);
        }
        var existing = byId.get();
        driver.setId(id);
        try {
            Patcher.driverPatcher(existing, driver);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return driverRepository.save(existing);
    }

    public void deleteById(Long id) {
        log.info("Delete Driver by ID: {}", id);
        Assert.notNull(id, "ID must be provided");
        var byId = findById(id);
        if (byId.isEmpty()) {
            throw new DriverNotFoundException(id);
        }
        driverRepository.deleteById(id);
    }
}
