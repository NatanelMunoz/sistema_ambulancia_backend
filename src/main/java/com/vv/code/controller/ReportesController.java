package com.vv.code.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vv.code.service.ReporteService;

/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 10/06/2023
 */
@RestController
public class ReportesController {

	private final ReporteService reporteService;

	public ReportesController(ReporteService reporteService) {
		super();
		this.reporteService = reporteService;
	}

	/**
	 * GENERA UN EXCEL CON EL REPORTE
	 * @return - RETORNA UN EXCEL PARA DESCARGAR
	 */
	@GetMapping
	@RequestMapping("/generarReporteDeServiciosRealizados")
	public ResponseEntity<InputStreamResource> reporteDeServiciosRealizados() {
		return reporteService.generarReporteServicio();
	}
}
