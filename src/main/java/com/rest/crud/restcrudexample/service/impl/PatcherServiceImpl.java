package com.rest.crud.restcrudexample.service.impl;

import com.rest.crud.restcrudexample.model.Driver;
import com.rest.crud.restcrudexample.service.PatcherService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class PatcherServiceImpl implements PatcherService {

    public void patchDriver(Driver existing, Driver incomplete) {
        var internClass = Driver.class;
        var driverFields = internClass.getDeclaredFields();
        for (Field field : driverFields) {
            field.setAccessible(true);
            try {
                var value = field.get(incomplete);
                if (value != null) {
                    field.set(existing, value);
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

            field.setAccessible(false);
        }
    }
}
