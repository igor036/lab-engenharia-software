/**
 * Author: Igor Joaquim dos Santos Lima 
 * Data: 26/10/2019
 */
package com.linecode.protocolo.cmd;

import javax.validation.constraints.Min;

public class CadastrarAvaliadorProtocoloCmd {
	
	@Min(value = 1, message = "ID do avaliador inválido.")
	private long idAvaliador;
	
	@Min(value = 1, message = "ID do protocolo inválido.")
	private long idProtocolo;
	
	public CadastrarAvaliadorProtocoloCmd() {
		//construtor utilizado pelo jackson
	} 

	public CadastrarAvaliadorProtocoloCmd(long idAvaliador,long idProtocolo) {
		this.idAvaliador = idAvaliador;
		this.idProtocolo = idProtocolo;
	}

	public long getIdAvaliador() {
		return idAvaliador;
	}

	public long getIdProtocolo() {
		return idProtocolo;
	}
}
