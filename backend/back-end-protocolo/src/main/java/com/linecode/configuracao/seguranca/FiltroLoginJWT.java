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

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecode.configuracao.seguranca.cmd.LoginCmd;

import com.linecode.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;

import io.jsonwebtoken.lang.Assert;


public class FiltroLoginJWT extends AbstractAuthenticationProcessingFilter  {
	
    private DocenteServico docenteServico;
    
	protected FiltroLoginJWT(String url, ApplicationContext contexto) {
		super(new AntPathRequestMatcher(url));
		this.docenteServico = contexto.getBean(DocenteServico.class);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		LoginCmd credentials = new ObjectMapper()
				.readValue(request.getInputStream(), LoginCmd.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getEmail(), 
						credentials.getSenha(), 
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
	
	@Override
    public AuthenticationManager getAuthenticationManager() {
        return (Authentication authentication) -> {

            String email = authentication.getName();
            String senha = authentication.getCredentials().toString();

            DocenteDto docente = docenteServico.getDocentePorEmailSenha(email, senha);
            Assert.notNull(docente, "Login e/ou Senha inválidos.");

            return docente;

        };
    }
	
}
