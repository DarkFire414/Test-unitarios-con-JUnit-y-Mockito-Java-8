package com.spring.mockito_example;

public class Calculadora {

    CalculadoraGoogle calculadoraGoogle;

    public int sumaPropia(int a, int b) {
        return calculadoraGoogle.sumar(a, b);
    }
}
