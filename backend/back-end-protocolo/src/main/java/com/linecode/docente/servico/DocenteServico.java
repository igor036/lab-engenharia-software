/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.linecode.docente.dao.DocenteDao;
import com.linecode.docente.dto.DocenteDto;

import io.jsonwebtoken.lang.Assert;

@Service
public class DocenteServico {
	
	@Autowired
	private DocenteDao docenteDao;
	
	/**
	 * Recupera os dados do docente apartir do email e da senha
	 * 
	 * @param {@link String} email
	 * @param {@link String} senha
	 */
	public DocenteDto getDocentePorEmailSenha(String email, String senha) {
		
		Assert.hasText(email, "Informe o email para efetuar o login");
		Assert.hasText(senha, "Informe a senha para efetuar o login");
		
		return docenteDao.getDocentePorEmailSenha(email, senha);
	}
	
	/**
	 * Retorna os dados do docente logado
	 * {@link DocenteDto} 
	 * 
	 */
	public DocenteDto getDadosDocenteLogado() {
		return (DocenteDto)SecurityContextHolder.getContext().getAuthentication();
	}
	
	@PreAuthorize("@autorizacaoServico.isAutorizado('ADMIN')")
	public String teste() {
		return null;
	}
}
