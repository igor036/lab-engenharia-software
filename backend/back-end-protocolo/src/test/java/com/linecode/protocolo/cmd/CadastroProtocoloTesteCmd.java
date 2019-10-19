package com.linecode.protocolo.cmd;

import java.time.LocalDate;
import java.util.List;

public class CadastroProtocoloTesteCmd extends CadastroProtocoloCmd {

	private String msgEsperada;

	public CadastroProtocoloTesteCmd(String justificativa, String resumoPt, String resumoEn, LocalDate dataInicio,
			LocalDate dataFim, List<PedidoProtocoloCmd> litaPedidoProtocolo, String msgEsperada) {
		super(justificativa, resumoPt, resumoEn, dataInicio, dataFim, litaPedidoProtocolo);
		this.msgEsperada = msgEsperada;
	}

	public String getMsgEsperada() {
		return msgEsperada;
	}
}
