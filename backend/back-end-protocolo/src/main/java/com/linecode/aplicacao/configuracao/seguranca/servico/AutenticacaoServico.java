package com.linecode.aplicacao.configuracao.seguranca.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;

import io.jsonwebtoken.lang.Assert;

@Component
public class AutenticacaoServico implements AuthenticationProvider {

	@Autowired
	private DocenteServico docenteServico; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
        String senha = authentication.getCredentials().toString();
        
        DocenteDto docente = docenteServico.getDocentePorEmailSenha(email, senha);        
        Assert.notNull(docente, "Login e/ou Senha inválidos.");

        return docente;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(DocenteDto.class);
	}
}
