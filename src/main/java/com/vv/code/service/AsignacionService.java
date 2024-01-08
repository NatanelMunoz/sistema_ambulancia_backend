package com.vv.code.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.model.dto.AsignacionDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.model.entity.Asignacion;
import com.vv.code.model.entity.Conductor;
import com.vv.code.model.entity.Peticion;
import com.vv.code.repository.AmbulanciaRepository;
import com.vv.code.repository.AsignacionRepository;
import com.vv.code.repository.ConductorRepository;
import com.vv.code.repository.PeticionesRepository;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 22/06/2023
 */
@Service
public class AsignacionService {

	private final AsignacionRepository asignacionRepository;
	private final PeticionesRepository peticionesRepository;
	private final AmbulanciaRepository ambulanciaRepository;
	private final ConductorRepository conductorRepository;

	public AsignacionService(AsignacionRepository asignacionRepository, PeticionesRepository peticionesRepository,
			AmbulanciaRepository ambulanciaRepository, ConductorRepository conductorRepository) {
		super();
		this.asignacionRepository = asignacionRepository;
		this.peticionesRepository = peticionesRepository;
		this.ambulanciaRepository = ambulanciaRepository;
		this.conductorRepository = conductorRepository;
	}

	/**
	 * REGISTRA UNA ASIGNACION
	 * @param asignacionDTO - JSON O XML DE LA ASIGNACIOON
	 * @return MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> registrarAsignacion(AsignacionDTO asignacionDTO) {
		if (asignacionDTO == null) {
			return new ResponseEntity<>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		// Peticion
		Optional<Peticion> peticion = peticionesRepository.findById(asignacionDTO.getPeticion().getId());
		Optional<Ambulancia> ambulancia = ambulanciaRepository.findById(asignacionDTO.getAmbulancia().getId());
		Optional<Conductor> conductor = conductorRepository.findById(asignacionDTO.getConductor().getId());

		if (!peticion.isPresent() || !ambulancia.isPresent() || !conductor.isPresent()) {
			return new ResponseEntity<String>("ERROR EN LOS RECURSOS", HttpStatus.NOT_FOUND);
		}

		Asignacion asignacion = new Asignacion();
		asignacion.setPeticion(peticion.get());
		asignacion.setAmbulancia(ambulancia.get());
		asignacion.setConductor(conductor.get());

		asignacionRepository.save(asignacion);

		return new ResponseEntity<String>("EXISTOSO", HttpStatus.CREATED);
	}

}
