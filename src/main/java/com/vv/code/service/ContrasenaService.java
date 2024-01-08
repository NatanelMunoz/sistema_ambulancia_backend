package com.vv.code.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.model.entity.Correo;
import com.vv.code.model.entity.Usuario;
import com.vv.code.repository.CorreoRespository;
import com.vv.code.repository.UsuarioRepository;
import com.vv.code.utils.Utils;

@Service
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class ContrasenaService {

	private final UsuarioRepository usuarioRepository;
	private final CorreoRespository correoRespository;
	private final Utils util;

	@Autowired
	public ContrasenaService(UsuarioRepository usuarioRepository, CorreoRespository correoRespository, Utils util) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.correoRespository = correoRespository;
		this.util = util;
	}

	/**
	 * RECUPERA LA CONTRASEÑA VIA CORREO ELECTRONICO
	 * @param correoElectronico - CORREO ELECTRONICO DEL USUARIO
	 * @return - MENSAJE DE CONFIRMACION
	 */
	public ResponseEntity<String> recuperarContrasena(String correoElectronico) {

		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(correoElectronico));
		Optional<Correo> correos = correoRespository.findById(1L);

		if (!usuario.isPresent()) {
			return new ResponseEntity<String>("NO SE HA ENCONTRADO EL RECURSO", HttpStatus.NOT_FOUND);
		}

		String subject = "RECUPERACION DE CONTRASEÑA DEL SGAR";
		String message = String.format(
				"Estimado: %s %s \n\t Hemos revisado su petición de recuperación de clave del SGAR y esta ha sido procesada, su contraseña es: %s",
				usuario.get().getNombres(), usuario.get().getApellidos(), usuario.get().getContrasena());

		boolean bandera = util.enviarCorreo(correos.get().getCorreo(), correos.get().getContrasena(),
				usuario.get().getCorreo(), subject, message);

		return (bandera) ? new ResponseEntity<>("Exitoso", HttpStatus.ACCEPTED)
				: new ResponseEntity<>("Fallido", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
