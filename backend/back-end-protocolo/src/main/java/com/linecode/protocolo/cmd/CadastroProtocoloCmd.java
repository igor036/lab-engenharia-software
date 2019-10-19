package com.linecode.protocolo.cmd;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CadastroProtocoloCmd {
	
	@NotBlank(message = "Informe a justificativa.")
	private String justificativa;
	
	@NotBlank(message = "Informe o resumo em português.")
	private String resumoPt;

	@NotBlank(message = "Informe o resumo em inglês.")
	private String resumoEn;
	
	@NotNull(message = "Informe a data de inicio.")
	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	
	@NotNull(message = "Informe a data fim.")
	
	@Min(value = 1, message = "Id da espécie inválido.")
	private long especie;
	
	@Min(value = 1, message = "A quantidade não pode ser menor que 1.")
	private int quantidade;
	
	@Min(value = 1, message = "Id do bioterio inválido.")
	private long bioterio;

	public CadastroProtocoloCmd() {
		
	}
	
	public CadastroProtocoloCmd(String justificativa,String resumoPt, String resumoEn, LocalDate dataInicio,
			LocalDate dataFim, long especie, int quantidade, long bioterio) {
		this.justificativa = justificativa;
		this.resumoPt = resumoPt;
		this.resumoEn = resumoEn;
		this.dataInicio = dataInicio;
		this.especie = especie;
		this.quantidade = quantidade;
		this.bioterio = bioterio;
		this.dataFim = dataFim;
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

	public long getEspecie() {
		return especie;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public long getBioterio() {
		return bioterio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
}
