package com.vv.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vv.code.model.entity.Conductor;
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

}
