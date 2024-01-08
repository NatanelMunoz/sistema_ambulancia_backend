package com.vv.code.model.dto;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class AsignacionDTO {

	private Long id;

	private PeticionDTO peticion;

	private AmbulanciaDTO ambulancia;

	private ConductorDTO conductor;

	public AsignacionDTO() {
		super();
	}

	public AsignacionDTO(Long id, PeticionDTO peticion, AmbulanciaDTO ambulancia, ConductorDTO conductor) {
		super();
		this.id = id;
		this.peticion = peticion;
		this.ambulancia = ambulancia;
		this.conductor = conductor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PeticionDTO getPeticion() {
		return peticion;
	}

	public void setPeticion(PeticionDTO peticion) {
		this.peticion = peticion;
	}

	public AmbulanciaDTO getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(AmbulanciaDTO ambulancia) {
		this.ambulancia = ambulancia;
	}

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}

}
