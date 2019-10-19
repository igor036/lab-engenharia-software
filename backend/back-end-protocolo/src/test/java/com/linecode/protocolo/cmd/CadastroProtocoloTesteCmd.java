package com.linecode.protocolo.cmd;

import java.time.LocalDate;

public class CadastroProtocoloTesteCmd extends CadastroProtocoloCmd {

	private String msgEsperada;

	public CadastroProtocoloTesteCmd(String justificativa,String resumoPt, String resumoEn, LocalDate dataInicio,
			 long especie, int quantidade, long bioterio, LocalDate dataFim, String msgEsperada) {
		super(justificativa, resumoPt, resumoEn, dataInicio, dataFim, especie, quantidade, bioterio);
	}

	public String getMsgEsperada() {
		return msgEsperada;
	}
}
