/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.compartilhado.dto.PaginacaoDto;
import com.linecode.protocolo.cmd.CadastrarAvaliadorProtocoloCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.dto.DetalheProtocoloDto;
import com.linecode.protocolo.dto.ListagemProtocoloDto;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;
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
	
	@GetMapping("lista-protocolo-docente-logado/pagina-atual/{paginaAtual}/quantidade-registros-pagina/{qtdRegistrosPagina}")
	public ResponseEntity<PaginacaoDto<ListagemProtocoloDto>> getListaProtocoloDocenteLogado(ConsultaListaProtocoloFiltro filtro,
            @PathVariable int paginaAtual, @PathVariable int qtdRegistrosPagina) {
	    return ResponseEntity.ok(protocoloServico.getListaProtocoloDocenteLogado(filtro, paginaAtual, qtdRegistrosPagina));
	}
	
	@GetMapping("detalhe/{idProtocolo}")
	public ResponseEntity<DetalheProtocoloDto> getDetalheProtocolo(@PathVariable long idProtocolo) {
	    return ResponseEntity.ok(protocoloServico.getDetalheProtocolo(idProtocolo));
	}
	
	@PostMapping("cadastrar-avaliador-protocolo")
	public ResponseEntity<String> cadastrarAvaliadorProtocolo(@RequestBody CadastrarAvaliadorProtocoloCmd cmd) {
		protocoloServico.cadastrarAvaliadorProtocolo(cmd);
		return ResponseEntity.ok("Avaliador cadastrado com sucesso!");
	}
}
