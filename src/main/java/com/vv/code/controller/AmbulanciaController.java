package com.vv.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.service.AmbulanciaService;

@RestController
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class AmbulanciaController {

	@Autowired
	private AmbulanciaService ambulanciaService;

	@GetMapping
	@RequestMapping(path = "/listarAmbulancias")
	/**
	 * @return - Una lista de ambulancias
	 */
	public ResponseEntity<List<AmbulanciaDTO>> listarAmbulancias() {
		return ambulanciaService.listarAmbulancias();
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarAmbulancia")
	/**
	 * 
	 * @param ambulanciaDTO - Se manda el objeto ambulancia en xml o json
	 * @return - Si fue exitoso o no
	 */
	public ResponseEntity<String> registrarAmbulancia(@RequestBody AmbulanciaDTO ambulanciaDTO) {
		return ambulanciaService.registrarAmbulancia(ambulanciaDTO);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/modificarAmbulancia")
	/**
	 * @param ambulanciaDTO - Se manda el objeto en JSON o XML
	 * @return - Si fue exitoso o no
	 */
	public ResponseEntity<String> modificarAmbulancia(@RequestParam("id") String id,
			@RequestBody AmbulanciaDTO ambulanciaDTO) {
		return ambulanciaService.modificarAmbulancia(id, ambulanciaDTO);
	}

	@DeleteMapping
	@RequestMapping(path = "/eliminarAmbulancia")
	/**
	 * @return - EXTOSO o FALLIDO depende de cual sea el caso de la operacion
	 */
	public ResponseEntity<String> deshabilitarAmbulancia(@RequestParam Long id) {
		return ambulanciaService.deshabilitarAmbulancia(id);
	}

}
