package com.spring.multiplicarUnitTest.operaciones;

import org.junit.Assert;
import org.junit.Test;

public class MultiplicadorTest {
    Multiplicador mult = new Multiplicador();

    @Test
    public void multiplicarTest() {
        int resultado = mult.multiplicar(2);
        int resultadoEsperado = 4;
        Assert.assertEquals(resultadoEsperado, resultado);
    }
}
