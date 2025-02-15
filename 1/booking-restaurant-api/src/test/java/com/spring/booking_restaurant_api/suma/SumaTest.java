package com.spring.booking_restaurant_api.suma;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumaTest {

    Suma sum = new Suma();

    @Before
    public void before() {
        System.out.println("Before");
    }

    @Test
    public void sumaTest() {
        System.out.println("SumaTest");
        int sumTest = sum.suma(1, 1);
        int resultadoEsperado = 2;
        assertEquals(resultadoEsperado, sumTest);
    }

    @After
    public void after() {
        System.out.println("After");
    }
}
