package com.vv.code.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.AmbulanciaDTOMapper;
import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.repository.AmbulanciaRepository;
import com.vv.code.repository.PeticionesRepository;

@SuppressWarnings("OverlyComplexMethod")
@Service
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class AmbulanciaService {

	private final AmbulanciaRepository ambulanciaRepository;
	private final PeticionesRepository peticionesRepository;
	private final AmbulanciaDTOMapper ambulanciaDTOMapper;

	public AmbulanciaService(AmbulanciaRepository ambulanciaRepository, PeticionesRepository peticionesRepository,
			AmbulanciaDTOMapper ambulanciaDTOMapper) {
		super();
		this.ambulanciaRepository = ambulanciaRepository;
		this.peticionesRepository = peticionesRepository;
		this.ambulanciaDTOMapper = ambulanciaDTOMapper;
	}

	public ResponseEntity<List<AmbulanciaDTO>> listarAmbulancias() {
		List<AmbulanciaDTO> listaAmbulancias = ambulanciaRepository.findAll().stream().map(ambulanciaDTOMapper)
				.collect(Collectors.toList());

		return new ResponseEntity<>(listaAmbulancias, HttpStatus.OK);
	}

	/**
	 * REGISTRA UNA AMBULANCIA
	 * @param ambulanciaDTO - OBEJETO DE TIPO AMBULANCIA
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> registrarAmbulancia(AmbulanciaDTO ambulanciaDTO) {
		if (ambulanciaDTO == null) {
			// throw new BadRequestException("SGAR-1", "Petición mal creada");
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setNumeroPlaca(ambulanciaDTO.getNumeroPlaca());
		ambulancia.setModelo(ambulanciaDTO.getModelo());
		ambulancia.setEstado(ambulanciaDTO.isEstado());
		ambulancia.setObservaciones(ambulancia.getObservaciones());
		ambulancia.setTipo(ambulanciaDTO.getTipo());

		ambulanciaRepository.save(ambulancia);
		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * MODIFICAR DATOS DE UNA AMBULANCIA
	 * @param id - ID DE LA AMBULANCIA
	 * @param ambulanciaDTO - OBJETO DE TIPO AMBULANCIA
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> modificarAmbulancia(String id, AmbulanciaDTO ambulanciaDTO) {
		if (id.equals("") || ambulanciaDTO == null) {
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		Optional<Ambulancia> ambulancia = ambulanciaRepository.findById(Long.valueOf(id));

		if (!ambulancia.isPresent()) {
			return new ResponseEntity<String>("NO SE ENCUENTRA ESE RECURSO", HttpStatus.NOT_FOUND);
		}

		ambulancia.get().setEstado(ambulanciaDTO.isEstado());
		ambulancia.get().setObservaciones(ambulanciaDTO.getObservaciones());

		ambulanciaRepository.save(ambulancia.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

	/**
	 * DESHABILITA UNA AMBULANCIA
	 * @param id - ID DE LA AMBULANCIA
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> deshabilitarAmbulancia(Long id) {
		if (id.equals("") || id == null) {
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		Optional<Ambulancia> ambulancia = ambulanciaRepository.findById(id);

		if (ambulancia.isPresent()) {
			ambulanciaRepository.deleteById(id);
			return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>("NO SE ENCUENTRA EL RECURSO", HttpStatus.NOT_FOUND);

	}

}
