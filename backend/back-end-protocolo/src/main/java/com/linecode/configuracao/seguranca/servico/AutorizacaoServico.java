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
import com.linecode.publico.enumerador.PerfilEnumerador;

@Service
public class AutorizacaoServico {
	
	/**
	 * Metodo que efetua autorizacao para
	 * apenas um perfil 
	 */
	public boolean isAutorizado(String ...autorizados) {
	    
	    DocenteDto docente = getDocenteLogado();
	    
	    for (String autorizado: autorizados) {
	        if (docente.getPerfil().toString().equals(autorizado)) {
	            return true;
	        }
	    }
	    
	    return false;
	}
	
	/**
	 * Retorna um valor booleando indicando
	 * se o usuário tem perfil de administração. 
	 * 
	 */
	public boolean isAutorizacaoAdmin() {
	    PerfilEnumerador perfil = getDocenteLogado().getPerfil();
	    return perfil.equals(PerfilEnumerador.ADMIN) || perfil.equals(PerfilEnumerador.COORDENADOR) || 
	            perfil.equals(PerfilEnumerador.SECRETARIA);
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
