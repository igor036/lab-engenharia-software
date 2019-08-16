/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecode.configuracao.seguranca.dto.LoginDto;
import com.linecode.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;


public class FiltroLoginJWT extends AbstractAuthenticationProcessingFilter  {
	
	protected FiltroLoginJWT(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		LoginDto credentials = new ObjectMapper()
				.readValue(request.getInputStream(), LoginDto.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						Collections.emptyList()
						)
				);
	}
	
	/**
	 * Metodo que é chamado quando a autenticação é realizada
	 * ele chama o metodo que ira adicionat o token JWT no 
	 * cabeçalho 'Authorization'
	 * */
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenJwtAutenticacaoServico.addAuthentication(response, auth.getName());
	}
	
}
