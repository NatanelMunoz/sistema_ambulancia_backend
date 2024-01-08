package com.vv.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.exceptions.BadRequestException;
import com.vv.code.service.ContrasenaService;

@RestController
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class ContrasenaController {

	private final ContrasenaService contrasenaService;

	@Autowired
	public ContrasenaController(ContrasenaService contrasenaService) {
		super();
		this.contrasenaService = contrasenaService;
	}

	@PostMapping
	@RequestMapping(path = "/recuperarContrasena")
	/**
	 * @param correoElectronico - correo a recuperar contraseña
	 * @return - EXITOSO O FALLIDO
	 */
	public ResponseEntity<String> recuperarContrasena(@RequestParam String correoElectronico) {

		if (correoElectronico.isEmpty()) {
			throw new BadRequestException("SGAR-1", "El campo no puede ser vacio");
		}

		return contrasenaService.recuperarContrasena(correoElectronico);
	}
}
