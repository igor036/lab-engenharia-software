package com.linecode.docente;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.servico.DocenteServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class DocenteCadastroStepsTest {
	
	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	@Spy
	private Validator validator;
	
	private List<Map<String, String>> dadosCadastro;
	
	@Quando("^desejar efetuar um cadastro informado um cadastro invalido com os seguintes dados$")
	public void desejar_efetuar_um_cadastro_informado_um_cadastro_invalido_com_os_seguintes_dados(
			List<Map<String, String>> dadosCadastro) throws Exception {
		this.dadosCadastro = dadosCadastro;
	}

	@Então("^devo receber uma Excecao Negocio exception\\$\\.$")
	public void devo_receber_uma_Excecao_Negocio_exception$() throws Exception {
		dadosCadastro.forEach( mapa ->  {
			try {
				
				CadastroDocenteCmd cmd = new CadastroDocenteCmd(
					mapa.get("email"), 
					mapa.get("nome"), 
					Integer.parseInt(mapa.get("idPerfil"))
				);
				
				docenteServico.cadastrarDocente(cmd);
				fail("Era pra ter lançado uma exceção de negocio");
			} catch (ExcecaoNegocio e) {
				assertTrue(mapa.get("MsgEsperada").contains(e.getMessage()));
			}
		});
	}
}
