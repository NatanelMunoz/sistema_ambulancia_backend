package com.vv.code.model.dto;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class HospitalDTO {

	private Long id;
	private String nombre;
	private boolean estado;

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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
