package com.ejercicio.mockito.controllers;

import com.ejercicio.mockito.services.SaludosService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SaludosControllerTest {
    // Dependencias
    @Mock
    SaludosService saludosService;

    @InjectMocks
    SaludosController saludosController;

    @Before
    public void init() {
        // Inicializar Mocks
        MockitoAnnotations.initMocks(this);
        // Simular métodos
        when(saludosService.saludo()).thenReturn("Buenos días");
    }

    @Test
    public void darSaludoTest() {
        ResponseEntity<String> expected = ResponseEntity.ok("Buenos días");
        assertEquals(expected, saludosController.darSaludo());
    }
}