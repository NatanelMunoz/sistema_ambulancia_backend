package com.vv.code.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vv.code.model.entity.Usuario;

/**
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
	Usuario findByEmail(@Param("correo") String correoElectronico);

	@Query("SELECT u FROM Usuario u INNER JOIN Roles r WHERE r.rol = :rol")
	List<Usuario> findByRol(@Param("rol") String rol);

	@Query("SELECT u FROM Usuario u WHERE u.rol.rol = :nombreRol")
	List<Usuario> findByRolName(@Param("nombreRol") String nombreRol);

	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena")
	Optional<Usuario> findByNombreUsuario(@Param("nombreUsuario") String nombreUsuario,
			@Param("contrasena") String contrasena);

}
