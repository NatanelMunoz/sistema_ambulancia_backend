package com.vv.code.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.model.entity.Ambulancia;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Service
public class AmbulanciaDTOMapper implements Function<Ambulancia, AmbulanciaDTO> {

	@Override
	public AmbulanciaDTO apply(Ambulancia ambulancia) {
		return new AmbulanciaDTO(ambulancia.getId(), ambulancia.getNumeroPlaca(), ambulancia.getModelo(),
				ambulancia.getTipo(), ambulancia.isEstado(), ambulancia.getObservaciones());
	}

}
