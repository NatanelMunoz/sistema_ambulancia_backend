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

import com.vv.code.model.dto.PeticionDTO;
import com.vv.code.service.PeticionService;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@RestController
public class PeticionesController {

	private final PeticionService peticionService;

	public PeticionesController(PeticionService peticionService) {
		super();
		this.peticionService = peticionService;
	}

	/**
	 * LISTA LAS PETICIONES DEL SISTEMA
	 * @return - LISTA DE PETICIONES HABILES Y NO HABILES
	 */
	@GetMapping
	@RequestMapping("/listarPeticiones")
	public ResponseEntity<List<PeticionDTO>> listarPeticiones() {
		return peticionService.listarPeticiones();
	}

	/**
	 * BUSCA UNA PETICION POR EL ID
	 * @param id - ID DE LA PETICION
	 * @return - JSON DE LA PETICION
	 */
	@GetMapping
	@RequestMapping("/buscarPeticion")
	public ResponseEntity<PeticionDTO> buscarPeticion(@RequestParam("id") Long id) {
		return peticionService.buscarPeticion(id);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarPeticion")
	public ResponseEntity<String> registrarPeticion(@RequestBody PeticionDTO peticionDTO) {
		return peticionService.registrarPeticion(peticionDTO);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/modificarPeticion")
	public ResponseEntity<String> modificarPeticion(@RequestParam("id") Long id, @RequestBody PeticionDTO peticionDTO) {
		return peticionService.modificarPeticion(id, peticionDTO);
	}

	/**
	 * ELIMINA UNA PETICION DEL SISTEMA
	 * @param id - ID DE LA PETICION
	 * @return - MENSAJE DE CONFIRMACION
	 */
	@DeleteMapping
	@RequestMapping(path = "/eliminarPeticion")
	public ResponseEntity<String> eliminarPeticion(@RequestParam Long id) {
		return peticionService.eliminarPeticion(id);
	}

}
