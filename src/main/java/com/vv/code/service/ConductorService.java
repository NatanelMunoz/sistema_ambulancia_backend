package com.vv.code.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.ConductorMapper;
import com.vv.code.model.dto.ConductorDTO;
import com.vv.code.model.entity.Conductor;
import com.vv.code.repository.ConductorRepository;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Service
public class ConductorService {

	private final ConductorRepository conductorRepository;
	private final ConductorMapper conductorMapper;

	public ConductorService(ConductorRepository conductorRepository, ConductorMapper conductorMapper) {
		super();
		this.conductorRepository = conductorRepository;
		this.conductorMapper = conductorMapper;
	}

	/**
	 * LISTAR CONDUCTORES
	 * @return - LISTA DE CONDUCTORES ACTIVOS O NO ACTIVOS
	 */
	public ResponseEntity<List<ConductorDTO>> listarConductores() {
		return new ResponseEntity<List<ConductorDTO>>(
				conductorRepository.findAll().stream().map(conductorMapper).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	/**
	 * REGISTRAR CONDUCTOR
	 * @param conductorDTO - JSON O XML DEL CONDUCTOR
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> registrarConductor(ConductorDTO conductorDTO) {
		if (conductorDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		try {
			Conductor conductor = new Conductor();
			conductor.setCedula(conductorDTO.getCedula());
			conductor.setNombre(conductorDTO.getNombre());
			conductor.setApellidos(conductorDTO.getApellidos());
			conductor.setCorreo(conductorDTO.getCorreo());
			conductor.setEstado(true);

			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			Date fechaNacimiento = formato.parse(conductorDTO.getFechaNacimiento());

			conductor.setFechaNacimiento(fechaNacimiento);
			conductor.setFechaContrato(new Date());
			conductor.setSexo(conductorDTO.getSexo());

			conductorRepository.save(conductor);
		} catch (ParseException p) {
			return new ResponseEntity<String>("ERROR EN LA FECHA", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * MODIFICA LOS DATOS DE UN CONDUCTOR
	 * @param id - ID DEL CONDUCTOR
	 * @param conductorDTO - JSON O XML DEL CONDUCTOR
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> modificarConductor(Long id, ConductorDTO conductorDTO) {
		if (id <= 0 || id == null || conductorDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Conductor> conductor = conductorRepository.findById(id);
		if (!conductor.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		try {
			conductor.get().setCedula(conductorDTO.getCedula());
			conductor.get().setNombre(conductorDTO.getNombre());
			conductor.get().setApellidos(conductorDTO.getApellidos());
			conductor.get().setCorreo(conductorDTO.getCorreo());
			conductor.get().setEstado(conductorDTO.isEstado());

			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			Date fechaNacimiento = formato.parse(conductorDTO.getFechaNacimiento());
			Date fechaContrato = formato.parse(conductorDTO.getFechaContrato());

			conductor.get().setFechaContrato(fechaContrato);
			conductor.get().setFechaNacimiento(fechaNacimiento);
			conductor.get().setSexo(conductorDTO.getSexo());

			conductorRepository.save(conductor.get());
		} catch (ParseException p) {
			return new ResponseEntity<String>("ERROR EN LA FECHA", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * ELIMINAR CONDUCTOR POR ID
	 * @param id - ID DEL CONDUCTOR
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> eliminarConductor(Long id) {
		if (id <= 0 || id == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Conductor> conductor = conductorRepository.findById(id);
		if (!conductor.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		conductorRepository.delete(conductor.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}
}
