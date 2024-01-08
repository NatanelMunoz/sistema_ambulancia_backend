package com.vv.code.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.PeticionMapper;
import com.vv.code.model.dto.PeticionDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.model.entity.Cliente;
import com.vv.code.model.entity.Conductor;
import com.vv.code.model.entity.Hospital;
import com.vv.code.model.entity.Peticion;
import com.vv.code.repository.AmbulanciaRepository;
import com.vv.code.repository.ClienteRepository;
import com.vv.code.repository.ConductorRepository;
import com.vv.code.repository.HospitalRepository;
import com.vv.code.repository.PeticionesRepository;

@Service
public class PeticionService {

	private final PeticionesRepository peticionesRepository;
	private final HospitalRepository hospitalRepository;
	private final ClienteRepository clienteRepository;
	private final AmbulanciaRepository ambulanciaRepository;
	private final ConductorRepository conductorRepository;
	private final PeticionMapper mapper;

	public PeticionService(PeticionesRepository peticionesRepository, HospitalRepository hospitalRepository,
			ClienteRepository clienteRepository, AmbulanciaRepository ambulanciaRepository,
			ConductorRepository conductorRepository, PeticionMapper mapper) {
		super();
		this.peticionesRepository = peticionesRepository;
		this.hospitalRepository = hospitalRepository;
		this.clienteRepository = clienteRepository;
		this.ambulanciaRepository = ambulanciaRepository;
		this.conductorRepository = conductorRepository;
		this.mapper = mapper;
	}

	/**
	 * LISTA PETICIONES
	 * @return - LISTA DE PETICIONES ACTIVAS Y NO ACTIVAS EN EL SISTEMA
	 */
	public ResponseEntity<List<PeticionDTO>> listarPeticiones() {
		List<Peticion> listaPeticiones = peticionesRepository.findAll();
		List<PeticionDTO> listaPeticionesDTO = listaPeticiones.stream().map(mapper).collect(Collectors.toList());

		return new ResponseEntity<List<PeticionDTO>>(listaPeticionesDTO, HttpStatus.OK);
	}

	/**
	 * BUSCA PETICION POR IDENTIFICADOR
	 * @param id - ID DE LA PETICION
	 * @return - JSON DE LA PETICION
	 */
	public ResponseEntity<PeticionDTO> buscarPeticion(Long id) {
		if (id <= 0 || id == null) {
			return new ResponseEntity<PeticionDTO>(new PeticionDTO(), HttpStatus.BAD_REQUEST);
		}
		Optional<Peticion> peticion = peticionesRepository.findById(id);

		if (!peticion.isPresent()) {
			return new ResponseEntity<PeticionDTO>(new PeticionDTO(), HttpStatus.NOT_FOUND);
		}

		List<Peticion> listaPeticion = new ArrayList<>();
		listaPeticion.add(peticion.get());

		Optional<PeticionDTO> peticionDTO = listaPeticion.stream().map(mapper).findFirst();

		return new ResponseEntity<PeticionDTO>(peticionDTO.get(), HttpStatus.OK);

	}

	/**
	 * REGISTRA UNA PETICION
	 * @param peticionDTO - JSON O XML DE LA PETICION
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> registrarPeticion(PeticionDTO peticionDTO) {
		if (peticionDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA TRAMA", HttpStatus.BAD_REQUEST);
		}

		Peticion peticion = new Peticion();
		peticion.setPuntoOrigen(peticionDTO.getPuntoOrigen());
		peticion.setPuntoDestino(peticionDTO.getPuntoDestino());
		peticion.setEstado(peticionDTO.isEstado());

		Optional<Hospital> hospital = hospitalRepository.findById(peticionDTO.getHospital().getId());
		Optional<Cliente> cliente = clienteRepository.findByCedula(peticionDTO.getCliente().getCedula());
		Optional<Set<Ambulancia>> setAmbulancia = ambulanciaRepository
				.findbyNumeroPlaca(peticionDTO.getAmbulancia().getNumeroPlaca());

		if (!hospital.isPresent() || !cliente.isPresent() || !setAmbulancia.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		peticion.setHospital(hospital.get());
		peticion.setCliente(cliente.get());
		peticion.setAmbulancia(setAmbulancia.get());

		peticionesRepository.save(peticion);

		Conductor conductor = new Conductor();
		conductor.setId(peticionDTO.getConductor().getId());
		conductor.setNombre(peticionDTO.getConductor().getNombre());
		conductor.setApellidos(peticionDTO.getConductor().getApellidos());
		conductor.setCedula(peticionDTO.getConductor().getCedula());
		conductor.setCorreo(peticionDTO.getConductor().getCorreo());
		conductor.setSexo(peticionDTO.getConductor().getSexo());
		conductor.setEstado(peticionDTO.getConductor().isEstado());

		for (Ambulancia ambulancia : setAmbulancia.get()) {
			ambulancia.setPeticion(peticion);
			ambulancia.setConductor(conductor);
			ambulanciaRepository.save(ambulancia);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * MODIFICA UNA PETICION POR ID
	 * @param id - ID DE LA PETICION
	 * @param peticionDTO - JSON O XML DE LA PETICION
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> modificarPeticion(Long id, PeticionDTO peticionDTO) {
		if (id <= 0 || id == null || peticionDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA TRAMA", HttpStatus.BAD_REQUEST);
		}

		Optional<Peticion> peticion = peticionesRepository.findById(id);
		peticion.get().setPuntoOrigen(peticionDTO.getPuntoOrigen());
		peticion.get().setPuntoDestino(peticionDTO.getPuntoDestino());
		peticion.get().setEstado(peticionDTO.isEstado());

		Optional<Hospital> hospital = hospitalRepository.findById(peticionDTO.getHospital().getId());
		Optional<Cliente> cliente = clienteRepository.findByCedula(peticionDTO.getCliente().getCedula());
		Optional<Set<Ambulancia>> setAmbulancia = ambulanciaRepository
				.findbyNumeroPlaca(peticionDTO.getAmbulancia().getNumeroPlaca());

		if (!hospital.isPresent() || !cliente.isPresent() || !setAmbulancia.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		peticion.get().setHospital(hospital.get());
		peticion.get().setCliente(cliente.get());
		peticion.get().setAmbulancia(setAmbulancia.get());

		peticionesRepository.save(peticion.get());

		for (Ambulancia ambulancia : setAmbulancia.get()) {
			ambulancia.setPeticion(peticion.get());
			ambulanciaRepository.save(ambulancia);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.OK);
	}

	/**
	 * ELIMINA UNA PETICION POR EL IDENTIFICADOR
	 * @param id - ID DE LA PETICION
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> eliminarPeticion(Long id) {
		if (id <= 0 | id == null) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Peticion> peticion = peticionesRepository.findById(id);

		if (!peticion.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE LA PETICION", HttpStatus.NOT_FOUND);
		}

		peticionesRepository.delete(peticion.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}
}
