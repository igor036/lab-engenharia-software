/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca.dto;

public class LoginDto {
	
	private String username;
	private String password;
	
	public LoginDto() {

	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
