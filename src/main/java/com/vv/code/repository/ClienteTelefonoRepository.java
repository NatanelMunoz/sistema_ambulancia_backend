package com.vv.code.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vv.code.model.entity.TelefonoCliente;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Repository
public interface ClienteTelefonoRepository extends JpaRepository<TelefonoCliente, Long> {

	@Query(value = "SELECT * FROM telefono_cliente WHERE cliente = :cliente_fk", nativeQuery = true)
	Optional<Set<TelefonoCliente>> findByCliente(@Param("cliente_fk") Long id);

}
