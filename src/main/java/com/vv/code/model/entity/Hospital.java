package com.vv.code.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class Hospital {

	@Id
	@SequenceGenerator(name = "hospital_id_seq", sequenceName = "hospital_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_id_seq")
	private Long id;
	private String nombre;

	@Column(name = "fecha_registro")
	private Date fechaRegistro;

	private boolean estado;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
	private Set<Cliente> clientes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital_fk")
	private Set<Peticion> peticiones;

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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Set<Peticion> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(Set<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

}
