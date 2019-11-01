package com.linecode.protocolo.cmd;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AvaliarProtocoloCmd {

	private boolean deferido;
	
	@NotBlank(message = "Informe a observação.")
	private String descricao;
	
	@Min(value = 1, message = "ID do protocolo inválido.")
	private long idProtocolo;
	
	public AvaliarProtocoloCmd() {
		// construtor utilizado pelo jackson
	}
	
	public AvaliarProtocoloCmd(boolean deferido, String descricao, long idProtocolo) {
		this.deferido = deferido;
		this.descricao = descricao;
		this.idProtocolo = idProtocolo;
	}

	public boolean isDeferido() {
		return deferido;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public long getIdProtocolo() {
		return idProtocolo;
	}
}
