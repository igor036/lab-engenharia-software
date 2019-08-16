/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.aplicacao.configuracao.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.linecode.aplicacao.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;

public class FiltroAutenticacaoJWT extends GenericFilterBean {
	
	/**
	 * Adicionar os dados do usuario autenticado
	 * no contexto de segurança do spring e filtra
	 * as requisições.
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		Authentication authentication = TokenJwtAutenticacaoServico
				.getAuthentication((HttpServletRequest) request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
	
}
