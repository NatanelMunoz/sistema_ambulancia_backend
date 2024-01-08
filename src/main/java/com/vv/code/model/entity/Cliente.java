package com.vv.code.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cedula;
	private String nombre;
	private String apellidos;
	private String sexo;
	private Date fechaNacimiento;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
	private Set<TelefonoCliente> telefonoClientes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private Set<Peticion> peticiones;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_fk")
	private Hospital hospital;

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<TelefonoCliente> getTelefonoClientes() {
		return telefonoClientes;
	}

	public void setTelefonoClientes(Set<TelefonoCliente> correosClientes) {
		this.telefonoClientes = correosClientes;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Set<Peticion> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(Set<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

}
