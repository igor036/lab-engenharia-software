/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 * 
 * Classe responsavel pelas autorizacoes
 * dos servicos.
 */
package com.linecode.configuracao.seguranca.servico;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.linecode.docente.dto.DocenteDto;

@Service
public class AutorizacaoServico {
	
	/**
	 * Metodo que efetua autorizacao para
	 * apenas um perfil 
	 */
	public boolean isAutorizado(String perfil) {
		return getDocenteLogado().getPerfil().equals(perfil);
	}
	
	/**
	 * Metodo que efetua a autorizacao
	 * apenas para quem esta logado 
	 */
	public boolean isAutenticado() {
		return getDocenteLogado() != null;
	}
	
	/**
	 * Metodo que retorna o docente logadod na aplicação
	 * 
	 * @return uma instancia de {@link DocenteDto}
	 */
	private DocenteDto getDocenteLogado() {
		return (DocenteDto) SecurityContextHolder.getContext().getAuthentication();
	}
}
