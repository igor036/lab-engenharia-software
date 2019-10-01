package com.linecode.docente;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.mockito.InjectMocks;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.cmd.CadastroDocenteTestCmd;
import com.linecode.docente.servico.DocenteServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CadastroDocenteStepsTest {
	
	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	private List<CadastroDocenteTestCmd> dadosCadastro;
	
	@Quando("^desejar efetuar um cadastro informado um cadastro invalido com os seguintes dados$")
	public void desejar_efetuar_um_cadastro_informado_um_cadastro_invalido_com_os_seguintes_dados(
			List<CadastroDocenteTestCmd> dadosCadastro) throws Exception {
		this.dadosCadastro = dadosCadastro;
	}

	@Então("^devo receber uma Excecao Negocio exception\\$\\.$")
	public void devo_receber_uma_Excecao_Negocio_exception$() throws Exception {
		dadosCadastro.forEach( cmd ->  {
			try {
				docenteServico.cadastrarDocente(cmd);
				fail("Era pra ter lançado uma exceção de negocio");
			} catch (ExcecaoNegocio e) {
				assertTrue(cmd.getMsgEsperada().contains(e.getMessage()));
			}
		});
	}
}
