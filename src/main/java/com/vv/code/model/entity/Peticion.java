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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class Peticion {

	@Id
	@SequenceGenerator(name = "peticion_id_seq", sequenceName = "peticion_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peticion_id_seq")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital")
	private Hospital hospital_fk;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "peticion")
	private Set<Ambulancia> ambulancia;

	private String puntoOrigen;
	private String puntoDestino;
	private Date fechaRegistro;
	private boolean estado;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "peticion")
	private Asignacion asignacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital_fk;
	}

	public void setHospital(Hospital hospital) {
		this.hospital_fk = hospital;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Ambulancia> getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Set<Ambulancia> ambulancia) {
		this.ambulancia = ambulancia;
	}

	public String getPuntoOrigen() {
		return puntoOrigen;
	}

	public void setPuntoOrigen(String puntoOrigen) {
		this.puntoOrigen = puntoOrigen;
	}

	public String getPuntoDestino() {
		return puntoDestino;
	}

	public void setPuntoDestino(String puntoDestino) {
		this.puntoDestino = puntoDestino;
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

	public Hospital getHospital_fk() {
		return hospital_fk;
	}

	public void setHospital_fk(Hospital hospital_fk) {
		this.hospital_fk = hospital_fk;
	}

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

}
