package com.vv.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vv.code.model.entity.Cliente;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "SELECT * FROM cliente WHERE cedula = :cedula", nativeQuery = true)
	Optional<Cliente> findByCedula(@Param("cedula") String cedula);

}
