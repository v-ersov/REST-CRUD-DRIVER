package com.rest.crud.restcrudexample.service;

import com.rest.crud.restcrudexample.model.Driver;

public interface PatcherService {

    void patchDriver(Driver existing, Driver incomplete);

}
