package com.linecode.protocolo.cmd;

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
