package com.vv.code.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.model.entity.Cliente;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Component
public class ClienteMapper implements Function<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO apply(Cliente t) {
		return new ClienteDTO(t.getId(), t.getCedula(), t.getNombre(), t.getApellidos(), null, t.getSexo(),
				String.valueOf(t.getFechaNacimiento()));
	}

}
