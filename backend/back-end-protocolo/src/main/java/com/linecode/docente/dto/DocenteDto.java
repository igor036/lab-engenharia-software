/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 * 
 * Classe que representa o docente logado.
 */
package com.linecode.docente.dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class DocenteDto implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	@JsonAlias("matricula")
	private long matricula;
	
	@JsonAlias("nome")
	private String nome;
	
	@JsonAlias("email")
	private String email;
	
	@JsonAlias("perfil")
	private String perfil;
	
	@JsonIgnore
	private boolean autenticado;
	
	public DocenteDto(long matricula, String nome, String email, String perfil) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.perfil = perfil;
		this.autenticado = true;
	}
	
	public DocenteDto() {
	    
	}
	
	public long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPerfil() {
		return perfil;
	}

	@Override
	public String getName() {
		return email;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(() -> perfil);
	}

	@Override
	@JsonIgnore
	public Object getCredentials() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getDetails() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return autenticado;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.autenticado = false;
	}
}
