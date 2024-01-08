package com.vv.code.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.model.dto.AsignacionDTO;
import com.vv.code.service.AsignacionService;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@RestController
public class AsignacionController {

	private final AsignacionService asignacionService;

	public AsignacionController(AsignacionService asignacionService) {
		super();
		this.asignacionService = asignacionService;
	}

	/**
	 * REGISTRA ASIGNACION
	 * @param asignacionDTO- OBJETO PeticionDTO
	 * @return - MENSAJE DE CONFIRMACION
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping("/registrarAsignacion")
	private ResponseEntity<String> registrarAsignacion(@RequestBody AsignacionDTO asignacionDTO) {
		return asignacionService.registrarAsignacion(asignacionDTO);
	}
}