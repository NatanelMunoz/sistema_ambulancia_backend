package com.vv.code.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
/**
 * @author Natanael Muñoz
 * @version 1.2 Date: 09/06/2023
 */
public class Utils {

	private Properties properties;
	private Session session;
	private MimeMessage message;

	/**
	 * @param correoRemitente    - Correo desde donde se enviara.
	 * @param contrasena         - Contraseña del remitente
	 * @param correoDestinatario - Correo a donde llegará la información
	 * @param tema               - Titulo del correo
	 * @param mensaje            - Cuerpo del correo
	 * @return Un booleano que avisa si se envio correctamente.
	 */
	public boolean enviarCorreo(String correoRemitente, String contrasena, String correoDestinatario, String tema,
			String mensaje) {
		boolean bandera;
		try {
			properties = new Properties();

			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.smtp.port", "587");
			properties.setProperty("mail.smtp.user", correoRemitente);
			properties.setProperty("mail.smtp.auth", "true");

			session = Session.getDefaultInstance(properties);

			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoRemitente));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
			message.setSubject(tema);
			message.setText(mensaje, "ISO-8859-1", "html");

			Transport transport = session.getTransport("smtp");
			transport.connect((String) properties.get("mail.smtp.user"), contrasena);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();

			bandera = true;
		} catch (Exception e) {
			bandera = false;
			e.printStackTrace();
		}
		return bandera;
	}

	/**
	 * MÉTODO DE LA LOGICA PARA GENERAR REPORTE
	 * @return - EXCEL EN UN ByteArrayInputStream
	 */
	public ByteArrayInputStream generarReporteServicios() {

		String[] cabecera = { "Punto de origen", "Punto de destino", "Placa de la ambulancia", "Cedula del Conductor",
				"Estado" };
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		try (Workbook reporte = new XSSFWorkbook()) {
			Sheet hoja = reporte.createSheet("REPORTE");

			Row filaUno = hoja.createRow(0);

			for (int indice = 0; indice < cabecera.length; indice++) {
				Cell cell = filaUno.createCell(indice);
				cell.setCellValue(cabecera[indice]);
			}

			reporte.write(stream);
			reporte.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(stream.toByteArray());
	}

}
