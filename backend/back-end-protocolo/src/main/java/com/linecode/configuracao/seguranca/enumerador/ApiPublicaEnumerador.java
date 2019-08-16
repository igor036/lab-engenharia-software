/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca.enumerador;

/**
 * Enumerador responsavel por
 * representar todas URL's
 * publicas do sistema
 */

public enum ApiPublicaEnumerador {
	
	CADASTRO("/cadastrar"),
	LOGIN("/login");
	
	private String urlApi;
	
	ApiPublicaEnumerador(String urlApi) {
		this.urlApi = urlApi;
	}
	
	public String getUrlApi() {
		return urlApi;
	}
}
