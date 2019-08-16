/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.docente.servico.DocenteServico;

@RestController
@RequestMapping("teste")
public class TesteControlador {
	
	@Autowired
	private DocenteServico docenteServico;
	
	@GetMapping("/ola")
	public ResponseEntity<String> ola() {
		docenteServico.teste();
		return ResponseEntity.ok("ola");
	}
}
