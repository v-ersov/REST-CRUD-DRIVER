package com.rest.crud.restcrudexample.util;

import com.rest.crud.restcrudexample.model.Driver;

import java.lang.reflect.Field;

public class Patcher {

    public static void driverPatcher(Driver existing, Driver incomplete) throws IllegalAccessException {
        var internClass = Driver.class;
        var driverFields = internClass.getDeclaredFields();
        for (Field field : driverFields) {
            field.setAccessible(true);
            var value = field.get(incomplete);
            if (value != null) {
                field.set(existing, value);
            }
            field.setAccessible(false);
        }
    }
}
