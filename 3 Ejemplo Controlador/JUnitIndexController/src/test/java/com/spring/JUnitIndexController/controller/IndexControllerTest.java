package com.spring.JUnitIndexController.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class IndexControllerTest {

    @InjectMocks
    IndexController indexController;

    @Before
    public void setUp() {
        this.indexController = new IndexController();
    }

    /**
     * Caso de prueba cuando los parámetros de entrada son nulos
     */
    @Test
    public void welcomeParamsNullTest() {
        String[] params = null;
        String expectedResult = "params está vacío";
        String currentResult = indexController.welcome(params);
        Assert.assertEquals(expectedResult, currentResult);
    }

    /**
     * Caso de prueba para parámetros vacíos
     */
    @Test
    public void welcomeParamsEmptyTest() {
        String[] params = new String[3];
        String expectedResult =
                "params[0] = null\n" +
                "params[1] = null\n" +
                "params[2] = null\n";
        String currentResult = indexController.welcome(params);
        Assert.assertEquals(expectedResult, currentResult);
    }

    /**
     * Caso de prueba para parámetros con losngitud cero
     */
    @Test
    public void welcomeParamsLength0Test() {
        String[] params = new String[] {};
        String expectedResult = "params está vacío";
        String currentResult = indexController.welcome(params);
        Assert.assertEquals(expectedResult, currentResult);
    }
}
