package com.vv.code.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.ClienteMapper;
import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.model.entity.Cliente;
import com.vv.code.model.entity.Hospital;
import com.vv.code.model.entity.Peticion;
import com.vv.code.model.entity.TelefonoCliente;
import com.vv.code.repository.ClienteRepository;
import com.vv.code.repository.ClienteTelefonoRepository;
import com.vv.code.repository.HospitalRepository;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final HospitalRepository hospitalRepository;
	private final ClienteTelefonoRepository clienteTelefonoRepository;
	private final ClienteMapper clienteMapper;

	public ClienteService(ClienteRepository clienteRepository, HospitalRepository hospitalRepository,
			ClienteTelefonoRepository clienteTelefonoRepository, ClienteMapper clienteMapper) {
		super();
		this.clienteRepository = clienteRepository;
		this.hospitalRepository = hospitalRepository;
		this.clienteTelefonoRepository = clienteTelefonoRepository;
		this.clienteMapper = clienteMapper;
	}

	/**
	 * LISTA LOS CLIENTES ASOCIADOS AL HOSPITAL
	 * @return - LISTA DE LOS CLIENTES
	 */
	public ResponseEntity<List<ClienteDTO>> listarClientes() {
		List<ClienteDTO> listaCliente = clienteRepository.findAll().stream().map(clienteMapper)
				.collect(Collectors.toList());
		return new ResponseEntity<List<ClienteDTO>>(listaCliente, HttpStatus.OK);
	}

	/**
	 * BUSCA UN USUARIO POR NUMERO DE CEDULA
	 * @param cedula - NUMERO DE CEDULA DEL CLIENTE
	 * @return - JSON DEL CLIENTE
	 */
	public ResponseEntity<ClienteDTO> buscarClientePorCedula(String cedula) {
		if (cedula == null) {
			return new ResponseEntity<ClienteDTO>(new ClienteDTO(), HttpStatus.OK);
		}

		Optional<ClienteDTO> cliente = clienteRepository.findByCedula(cedula).stream().map(clienteMapper).findFirst();

		if (!cliente.isPresent()) {
			return new ResponseEntity<ClienteDTO>(new ClienteDTO(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ClienteDTO>(cliente.get(), HttpStatus.OK);
	}

	/**
	 * REGISTRAR CLIENTE
	 * @param clienteDTO - JSON O XML DEL CLIENTE
	 * @return - MENSAJE DE CONFIRMACION
	 * @throws ParseException - LANZAMIENTO DE ERROR DE PARSEO EN CUESTIO DE FECHAS
	 */
	public ResponseEntity<String> registrarCliente(ClienteDTO clienteDTO) throws ParseException {

		if (clienteDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA PETICION", HttpStatus.BAD_REQUEST);
		}

		Cliente cliente = new Cliente();
		Set<TelefonoCliente> hashSetTelefono = new HashSet<TelefonoCliente>();
		Set<Peticion> hashSetPeticiones = new HashSet<Peticion>();

		Optional<Hospital> hospital = hospitalRepository.findById(1L);

		if (!hospital.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL HOSPITAL", HttpStatus.NOT_FOUND);
		}

		cliente.setCedula(clienteDTO.getCedula());
		cliente.setNombre(clienteDTO.getNombres());
		cliente.setApellidos(clienteDTO.getApellidos());
		cliente.setSexo(clienteDTO.getSexo());
		cliente.setHospital(hospital.get());

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaNacimiento = formato.parse(clienteDTO.getFechaNacimiento());

		cliente.setFechaNacimiento(fechaNacimiento);
		cliente.setPeticiones(hashSetPeticiones);

		for (int indice = 0; indice < clienteDTO.getNumeroTelefonico().size(); indice++) {
			TelefonoCliente telefono = new TelefonoCliente();
			telefono.setClientes(cliente);
			telefono.setNumeroTelefonico(clienteDTO.getNumeroTelefonico().get(indice));
			hashSetTelefono.add(telefono);
			clienteTelefonoRepository.save(telefono);
		}

		cliente.setTelefonoClientes(hashSetTelefono);
		clienteRepository.save(cliente);

		for (TelefonoCliente telefono : hashSetTelefono) {
			clienteTelefonoRepository.save(telefono);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * MODIFICAR CLIENTE POR EL ID
	 * @param id - ID DEL CLIENTE
	 * @param clienteDTO - JSON O XML DEL CLIENTE
	 * @return - MENSAJE DE CONFIMACION
	 */
	public ResponseEntity<String> modificarCliente(Long id, ClienteDTO clienteDTO) {
		if (id == null || id <= 0) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		cliente.get().setNombre(clienteDTO.getNombres());
		cliente.get().setApellidos(clienteDTO.getApellidos());
		cliente.get().setSexo(clienteDTO.getSexo());

		Optional<Set<TelefonoCliente>> telefonoCliente = clienteTelefonoRepository.findByCliente(id);

		for (int indice = 0; indice < clienteDTO.getNumeroTelefonico().size(); indice++) {
			TelefonoCliente telefono = new TelefonoCliente();
			telefono.setClientes(cliente.get());
			telefono.setNumeroTelefonico(clienteDTO.getNumeroTelefonico().get(indice));
			telefonoCliente.get().add(telefono);
		}

		clienteRepository.save(cliente.get());

		for (TelefonoCliente telefono : telefonoCliente.get()) {
			clienteTelefonoRepository.save(telefono);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	/**
	 * ELIMINA UN CLIENTE DE SGAR
	 * @param id - ID DEL CLIENTE
	 * @return - MEMSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> eliminarCliente(Long id) {
		if (id == null || id <= 0) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		clienteRepository.deleteById(id);
		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

}
