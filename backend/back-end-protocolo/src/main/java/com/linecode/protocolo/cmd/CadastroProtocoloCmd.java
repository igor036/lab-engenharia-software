package com.linecode.protocolo.cmd;

import java.time.LocalDate;

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
	
	@NotNull(message = "Informe a data fim.")
	private LocalDate dataFim;

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
}
