package com.vv.prueba;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.vv.code.utils.Utils;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class CorreoTest {

	private final Utils utils = new Utils();

	@Test
	@Disabled
	/**
	 * @param remitente          - correo de donde se envia
	 * @param contrasena         - contraseña del remitente
	 * @param correoDestinatario - correo de donde se va a enviar
	 * @param subject            - El titulo del correo
	 * @param message            - Mensaje del correo
	 */
	public void enviarCoreo() {
		String correoRemitente = "natanael.munoz.s@gmail.com";
		String contrasena = "tdlpqbuulwzjilkq";
		String correoDestinatario = "natanael.munoz.s@gmail.com";
		String subject = "RECUPERACION DE CUENTA";
		String message = "Hola";
		boolean bandera = utils.enviarCorreo(correoRemitente, contrasena, correoDestinatario, subject, message);
		assertTrue(bandera);
	}

}
