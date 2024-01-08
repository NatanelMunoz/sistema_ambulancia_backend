package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vv.code.model.entity.Correo;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
public interface CorreoRespository extends JpaRepository<Correo, Long> {

}
