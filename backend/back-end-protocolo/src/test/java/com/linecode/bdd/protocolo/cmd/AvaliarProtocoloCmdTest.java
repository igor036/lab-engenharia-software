package com.linecode.bdd.protocolo.cmd;

import com.linecode.protocolo.cmd.AvaliarProtocoloCmd;

public class AvaliarProtocoloCmdTest extends AvaliarProtocoloCmd {
	
	private String msgEsperada;
	
	public AvaliarProtocoloCmdTest(boolean deferido, String descricao, long idProtocolo, String msgEsperada) {
		super(deferido, descricao, idProtocolo);
		this.msgEsperada = msgEsperada;
	}
	
	public String getMsgEsperada() {
		return msgEsperada;
	}
}
