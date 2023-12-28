package com.rest.crud.restcrudexample.service.impl;

import com.rest.crud.restcrudexample.exception.DriverNotFoundException;
import com.rest.crud.restcrudexample.model.Driver;
import com.rest.crud.restcrudexample.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverService;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private PatcherServiceImpl patcherService;

    @Test
    void testFindByIdWhenIdIsNull() {
        final var expectedMessage = "ID must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.findById(null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("ID must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testSaveWhenDriverIsNull() {
        final var expectedMessage = "Driver must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.save(null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("Driver must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdateWhenIdIsNull() {
        final var expectedMessage = "ID must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.update(null, null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("ID must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdateWhenDriverIsNull() {
        final var expectedMessage = "Driver must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.update(1L, null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("Driver must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdateWhenIdNotEqualsDriverId() {
        final var expectedMessage = "ID must be equal Driver ID";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.update(1L, new Driver(2L, "Test", 1)),
                "Must throw IllegalArgumentException"
        );

        assertEquals("ID must be equal Driver ID",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdateWhenDriverNotFound() {
        final var expectedMessage = "ID must be equal Driver ID";
        final var id = 1L;

        when(driverRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.update(id, new Driver(id, "Test", 1)),
                "Must throw DriverNotFoundException"
        );

        assertEquals(String.format("Driver not found with ID: %s", id),
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdatePartialWhenIdIsNull() {
        final var expectedMessage = "ID must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.updatePartial(null, null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("ID must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdatePartialWhenDriverIsNull() {
        final var expectedMessage = "Driver must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.updatePartial(1L, null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("Driver must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdatePartialWhenDriverNotFound() {
        final var expectedMessage = "ID must be equal Driver ID";
        final var id = 1L;

        when(driverRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.updatePartial(id, new Driver(id, "Test", 1)),
                "Must throw DriverNotFoundException"
        );

        assertEquals(String.format("Driver not found with ID: %s", id),
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testUpdatePartialTrowsRunTimeException() {
        final var id = 1L;

        var existing = new Driver(id, "Test", 1);
        var incomplete = Driver.builder().name("New Test").build();

        when(driverRepository.findById(id)).thenReturn(Optional.of(existing));
        doThrow(RuntimeException.class).when(patcherService).patchDriver(existing, incomplete);

        assertThrows(
                RuntimeException.class,
                () -> driverService.updatePartial(id, incomplete),
                "Must throw RuntimeException"
        );
    }

    @Test
    void testDeleteByIdWhenIdInNull() {
        final var expectedMessage = "ID must be provided";
        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> driverService.deleteById(null),
                "Must throw IllegalArgumentException"
        );

        assertEquals("ID must be provided",
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }

    @Test
    void testDeleteByIdWhenDriverNotFound() {
        final var id = 1L;
        final var expectedMessage = String.format("Driver not found with ID: %s", id);

        when(driverRepository.findById(id)).thenReturn(Optional.empty());

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.deleteById(id),
                "Must throw DriverNotFoundException"
        );

        assertEquals(String.format("Driver not found with ID: %s", id),
                exception.getMessage(),
                String.format("Message must be %s", expectedMessage)
        );
    }
}