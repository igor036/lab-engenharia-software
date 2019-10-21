package com.linecode.protocolo.cmd;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroProtocoloCmd {
	
	@NotBlank(message = "Informe a justificativa.")
	private String justificativa;
	
	@NotBlank(message = "Informe o resumo em português.")
	private String resumoPt;

	@NotBlank(message = "Informe o resumo em inglês.")
	private String resumoEn;
	
	@NotNull(message = "Informe a data de inicio.")
	private LocalDate dataInicio;
	
	@NotNull(message = "Informe a data fim.")
	private LocalDate dataFim;
	
	@Size(min = 1, message = "Informe ao menos um pedido")
	@NotNull(message = "Informe ao menos um pedido")
	private List<PedidoProtocoloCmd> litaPedidoProtocolo;

	public CadastroProtocoloCmd() {
		
	}
	
	public CadastroProtocoloCmd(String justificativa,String resumoPt, String resumoEn, LocalDate dataInicio,
			LocalDate dataFim,  List<PedidoProtocoloCmd> litaPedidoProtocolo) {
		this.justificativa = justificativa;
		this.resumoPt = resumoPt;
		this.resumoEn = resumoEn;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.litaPedidoProtocolo = litaPedidoProtocolo;
	}


	public String getJustificativa() {
		return justificativa;
	}

	public String getResumoPt() {
		return resumoPt;
	}

	public String getResumoEn() {
		return resumoEn;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}
	
	public  List<PedidoProtocoloCmd> getLitaPedidoProtocolo() {
		return litaPedidoProtocolo;
	}
}
