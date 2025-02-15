package com.ejercicio.mockito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ejercicio.mockito.services.SaludosService;

@Controller
public class SaludosController {

	@Autowired
	SaludosService greetingService;

	@GetMapping("/buenos-dias")
	public ResponseEntity<String> darSaludo() {
		return ResponseEntity.ok(greetingService.saludo());
	}
}