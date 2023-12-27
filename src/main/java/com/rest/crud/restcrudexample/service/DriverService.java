package com.rest.crud.restcrudexample.service;

import com.rest.crud.restcrudexample.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> findAll();

    Optional<Driver> findById(Long id);

    Driver save(Driver driver);

    Driver update(Long id, Driver driver);

    Driver updatePartial(Long id, Driver driver);

    void deleteById(Long id);

}
