package com.linecode.protocolo.cmd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastroProtocoloTesteCmd extends CadastroProtocoloCmd {

	private String msgEsperada;

	public CadastroProtocoloTesteCmd(String justificativa, String resumoPt, String resumoEn, LocalDate dataInicio,
			LocalDate dataFim, String msgEsperada) {
		super(justificativa, resumoPt, resumoEn, dataInicio, dataFim);
		this.msgEsperada = msgEsperada;
	}

	public String getMsgEsperada() {
		return msgEsperada;
	}
}
