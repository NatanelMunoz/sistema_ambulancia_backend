package com.vv.code.model.dto;

import java.util.List;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class ClienteDTO {

	private Long id;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String correo;
	private String sexo;
	private String fechaNacimiento;
	private List<String> numeroTelefonico;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String cedula, String nombres, String apellidos, String correo, String sexo,
			String fechaNacimiento) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<String> getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(List<String> numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

}
