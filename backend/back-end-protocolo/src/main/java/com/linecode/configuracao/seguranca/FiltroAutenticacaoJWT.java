/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.linecode.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;

public class FiltroAutenticacaoJWT extends GenericFilterBean {
	
	/**
	 * Adicionar os dados do usuario autenticado
	 * no contexto de segurança do spring e filtra
	 * as requisições.
	 * */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
	
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		Authentication authentication = TokenJwtAutenticacaoServico
				.getAuthentication((HttpServletRequest) servletRequest);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
	}
	
}
