package com.vv.code.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Entity
@Table
public class Asignacion {

	@Id
	@SequenceGenerator(name = "asignacion_id_seq", sequenceName = "asignacion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asignacion_id_seq")
	private Long id;

	@OneToOne
	@JoinColumn(name = "peticion_fk")
	private Peticion peticion;

	@OneToOne
	@JoinColumn(name = "ambulancia_fk")
	private Ambulancia ambulancia;

	@OneToOne
	@JoinColumn(name = "conductor_fk")
	private Conductor conductor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Peticion getPeticion() {
		return peticion;
	}

	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

}
