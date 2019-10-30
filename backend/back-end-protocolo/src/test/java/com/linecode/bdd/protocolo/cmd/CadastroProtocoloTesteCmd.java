package com.linecode.bdd.protocolo.cmd;

import java.time.LocalDate;
import java.util.List;

import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.cmd.PedidoProtocoloCmd;

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
