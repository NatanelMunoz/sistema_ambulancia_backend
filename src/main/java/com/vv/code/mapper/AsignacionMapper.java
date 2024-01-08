package com.vv.code.mapper;

import java.util.function.Function;

import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.model.dto.AsignacionDTO;
import com.vv.code.model.dto.ConductorDTO;
import com.vv.code.model.dto.PeticionDTO;
import com.vv.code.model.entity.Asignacion;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public class AsignacionMapper implements Function<Asignacion, AsignacionDTO> {

	@Override
	public AsignacionDTO apply(Asignacion t) {

		AsignacionDTO asignacion = new AsignacionDTO();
		asignacion.setId(t.getId());

		// Conductor
		ConductorDTO conductor = new ConductorDTO();
		conductor.setId(t.getConductor().getId());
		conductor.setNombre(t.getConductor().getNombre());
		conductor.setApellidos(t.getConductor().getApellidos());
		conductor.setCorreo(t.getConductor().getCorreo());
		conductor.setSexo(t.getConductor().getSexo());
		conductor.setEstado(t.getConductor().isEstado());

		// Ambulancia
		AmbulanciaDTO ambulanciaDTO = new AmbulanciaDTO();
		ambulanciaDTO.setId(t.getAmbulancia().getId());
		ambulanciaDTO.setModelo(t.getAmbulancia().getModelo());
		ambulanciaDTO.setNumeroPlaca(t.getAmbulancia().getNumeroPlaca());
		ambulanciaDTO.setTipo(t.getAmbulancia().getTipo());
		ambulanciaDTO.setObservaciones(t.getAmbulancia().getObservaciones());

		// Peticion
		PeticionDTO peticionDTO = new PeticionDTO();
		peticionDTO.setId(t.getId());
		peticionDTO.setPuntoOrigen(t.getPeticion().getPuntoOrigen());
		peticionDTO.setPuntoDestino(t.getPeticion().getPuntoDestino());
		peticionDTO.setEstado(t.getPeticion().isEstado());

		asignacion.setConductor(conductor);
		asignacion.setAmbulancia(ambulanciaDTO);
		asignacion.setPeticion(peticionDTO);

		return asignacion;

	}

}
