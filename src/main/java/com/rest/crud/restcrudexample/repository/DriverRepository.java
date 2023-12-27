package com.rest.crud.restcrudexample.repository;

import com.rest.crud.restcrudexample.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
