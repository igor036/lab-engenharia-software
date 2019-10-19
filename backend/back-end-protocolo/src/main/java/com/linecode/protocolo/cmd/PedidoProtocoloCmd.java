package com.linecode.protocolo.cmd;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoProtocoloCmd {
	
	@Min(value = 1, message =  "Id espécie inválido.")
	private long idEspecie;
	
	@Min(value = 1, message =  "Quantidade espécie inválida.")
	private int quantidade;
	
	@Min(value = 1, message =  "Id biotério inválido.")
	private long idBioterio;
	
	@JsonIgnore
	private long idProtocolo;
	
	public PedidoProtocoloCmd() {
		
	}
	
	public PedidoProtocoloCmd(long idEspecie, int quantidade, long idBioterio) {
		this.idEspecie = idEspecie;
		this.quantidade = quantidade;
		this.idBioterio = idBioterio;
		this.idProtocolo = idBioterio;
	}

	public long getIdEspecie() {
		return idEspecie;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public long getIdBioterio() {
		return idBioterio;
	}
	
	public long getIdProtocolo() {
		return  idProtocolo;
	}
	
	public void setIdProtocolo(long idProtocolo) {
		this.idProtocolo = idProtocolo;
	}
}
