package com.ejercicio.mockito.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class SaludosServiceTest {
    @InjectMocks
    SaludosService saludosService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saludoTest() {
        assertEquals("Buenos dias", saludosService.saludo());
    }
}