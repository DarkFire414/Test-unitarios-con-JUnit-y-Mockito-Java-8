package com.spring.mockito_example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class CalculadoraTest {

    // Clase dependencia
    @Mock
    CalculadoraGoogle calculadoraGoogle;

    // Clase a testear
    @InjectMocks
    Calculadora calculadora;

    @Before
    public void init() {
        // Inicializar los Mocks
        MockitoAnnotations.initMocks(this);

        // Se emula la funcionalidad del método sumar
        when(calculadoraGoogle.sumar(5, 5)).thenReturn(10);
    }

    @Test
    public void sumaPropiaTest() {
        Assert.assertEquals(10, calculadora.sumaPropia(5, 5));
    }

}
