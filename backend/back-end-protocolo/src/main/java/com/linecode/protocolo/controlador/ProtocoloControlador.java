/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.servico.ProtocoloServico;

@RestController
@RequestMapping("protocolo")
public class ProtocoloControlador {
	
	@Autowired
	private ProtocoloServico protocoloServico;
	
	@PostMapping("cadastrar")
	public ResponseEntity<String> cadastrarProtocolo(@RequestBody CadastroProtocoloCmd cmd) {
		protocoloServico.cadastrarProtocolo(cmd);
		return ResponseEntity.ok("Protocolo cadastrado com sucesso!");
	}
}
