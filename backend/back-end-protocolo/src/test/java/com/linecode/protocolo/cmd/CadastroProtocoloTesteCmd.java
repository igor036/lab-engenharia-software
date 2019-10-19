package com.linecode.protocolo.cmd;

import java.time.LocalDate;
import java.util.List;

public class CadastroProtocoloTesteCmd extends CadastroProtocoloCmd {

	private String msgEsperada;

	public CadastroProtocoloTesteCmd(String justificativa, String resumoPt, String resumoEn, LocalDate dataInicio,
			LocalDate dataFim, String msgEsperada, List<PedidoProtocoloCmd> litaPedidoProtocolo) {
		super(justificativa, resumoPt, resumoEn, dataInicio, dataFim, litaPedidoProtocolo);
	}

	public String getMsgEsperada() {
		return msgEsperada;
	}
}
