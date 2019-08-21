/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;

@RestController
@RequestMapping("docente")
public class DocenteControlador {
	
	@Autowired
	private DocenteServico docenteServico;
	
	@GetMapping("dados-docente-logado")
	public ResponseEntity<DocenteDto> getDadosDocenteLogado() {
		return ResponseEntity.ok(docenteServico.getDadosDocenteLogado());
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<String> cadastrar(@RequestBody CadastroDocenteCmd cmd) {
	    
	    docenteServico.cadastrarDocente(cmd);
	    
	    return ResponseEntity.ok("MEU OVO");
	}
}
