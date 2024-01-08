package com.vv.code.controller;

import java.text.ParseException;
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

import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.service.ClienteService;

@RestController
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@GetMapping
	@RequestMapping(path = "/listarCliente")
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		return clienteService.listarClientes();
	}

	@GetMapping
	@RequestMapping(path = "/buscarClientePorCedula")
	public ResponseEntity<ClienteDTO> buscarClientePorCedula(@RequestParam("cedula") String cedula) {
		return clienteService.buscarClientePorCedula(cedula);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/registrarCliente")
	public ResponseEntity<String> registrarCliente(@RequestBody ClienteDTO clienteDTO) throws ParseException {
		return clienteService.registrarCliente(clienteDTO);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/actualizarCliente")
	public ResponseEntity<String> modificarCliente(@RequestParam("id") Long id, @RequestBody ClienteDTO clienteDTO) {
		return clienteService.modificarCliente(id, clienteDTO);
	}

	@DeleteMapping
	@RequestMapping(path = "/eliminarCliente")
	public ResponseEntity<String> eliminarCliente(@RequestParam("id") Long id) {
		return clienteService.eliminarCliente(id);
	}

}
