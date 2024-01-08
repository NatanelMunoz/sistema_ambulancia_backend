package com.vv.code.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.model.dto.ConductorDTO;
import com.vv.code.service.ConductorService;

@RestController
public class ConductorControlle {

	private final ConductorService conductorService;

	public ConductorControlle(ConductorService conductorService) {
		super();
		this.conductorService = conductorService;
	}

	@GetMapping
	@RequestMapping("/listarConductores")
	public ResponseEntity<List<ConductorDTO>> listarConductor() {
		return conductorService.listarConductores();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarConductor")

	public ResponseEntity<String> registrarAmbulancia(@RequestBody ConductorDTO conductorDTO) {
		return conductorService.registrarConductor(conductorDTO);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/modificarConductor")
	public ResponseEntity<String> modificarAmbulancia(@RequestParam("id") Long id,
			@RequestBody ConductorDTO conductorDTO) {
		return conductorService.modificarConductor(id, conductorDTO);
	}

	@DeleteMapping
	@RequestMapping(path = "/eliminarConductor")
	public ResponseEntity<String> deshabilitarAmbulancia(@RequestParam Long id) {
		return conductorService.eliminarConductor(id);
	}

}
