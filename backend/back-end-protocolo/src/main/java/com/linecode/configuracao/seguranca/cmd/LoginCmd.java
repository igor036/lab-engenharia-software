/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca.cmd;

public class LoginCmd {
	
	private String email;
	private String senha;
	
	public LoginCmd() {
		//construtor padrao do jackson
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setUsername(String email) {
		this.email = email;
	}
	
	public void setPassword(String senha) {
		this.senha = senha;
	}
}
