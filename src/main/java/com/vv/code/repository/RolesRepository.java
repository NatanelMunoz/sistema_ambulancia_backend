package com.vv.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vv.code.model.entity.Roles;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public interface RolesRepository extends JpaRepository<Roles, Long> {

	@Query(value = "SELECT * FROM roles r WHERE r.rol = :rol", nativeQuery = true)
	Roles findByName(@Param("rol") String rol);

	@Query("SELECT r FROM Roles r WHERE r.id = :id")
	Optional<Roles> findById(@Param("id") Long id);

}
