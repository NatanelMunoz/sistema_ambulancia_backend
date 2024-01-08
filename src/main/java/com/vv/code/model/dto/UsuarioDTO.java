package com.vv.code.model.dto;

import java.util.Date;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class UsuarioDTO {

	private Long id;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String sexo;
	private String correo;
	private String fechaNacimiento;
	private Date fechaContrato;

	private String nombreUsuario;
	private String contrasena;

	private String tipo;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String cedula, String nombres, String apellidos, String sexo, String correo,
			String fechaNacimiento, Date fechaContrato, String nombreUsuario, String tipo) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.sexo = sexo;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaContrato = fechaContrato;
		this.nombreUsuario = nombreUsuario;
		this.tipo = tipo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
