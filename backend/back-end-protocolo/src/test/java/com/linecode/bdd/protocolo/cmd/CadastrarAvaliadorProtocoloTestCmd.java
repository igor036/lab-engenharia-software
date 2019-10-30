package com.linecode.bdd.protocolo.cmd;

import com.linecode.protocolo.cmd.CadastrarAvaliadorProtocoloCmd;

public class CadastrarAvaliadorProtocoloTestCmd extends CadastrarAvaliadorProtocoloCmd {
	
	private String msgEsperada;
	
	public CadastrarAvaliadorProtocoloTestCmd(long idAvaliador, long idProtocolo, String msgEsperada) {
		super(idAvaliador, idProtocolo);
		this.msgEsperada = msgEsperada;
	}
	
	public  String getMsgEsperada() {
		return msgEsperada;
	}
}
