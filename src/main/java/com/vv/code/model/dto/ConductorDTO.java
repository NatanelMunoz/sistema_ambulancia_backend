package com.vv.code.model.dto;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class ConductorDTO {

	private Long id;
	private String cedula;
	private String nombre;
	private String apellidos;
	private String correo;
	private String fechaNacimiento;
	private String fechaContrato;
	private String sexo;
	private boolean estado;

	public ConductorDTO() {
		super();
	}

	public ConductorDTO(Long id, String cedula, String nombre, String apellidos, String correo, String fechaNacimiento,
			String fechaContrato, String sexo, boolean estado) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaContrato = fechaContrato;
		this.sexo = sexo;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
