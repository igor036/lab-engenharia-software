package com.linecode.protocolo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.mockito.InjectMocks;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.cmd.CadastroDocenteTestCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloTesteCmd;
import com.linecode.protocolo.servico.ProtocoloServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CadastroProtocoloStepsTest {
	
	@InjectMocks
	private ProtocoloServico protocoloServico = new ProtocoloServico();
	
	private List<CadastroProtocoloTesteCmd> dadosCadastro;
	
	@Quando("^desejar efetuar um cadastro de um determinado protocolo informando um cadastro invalido com os seguintes dados$")
	public void desejar_efetuar_um_cadastro_de_um_determinado_protocolo_informando_um_cadastro_invalido_com_os_seguintes_dados(
			List<CadastroProtocoloTesteCmd> dadosCadastro) throws Exception {
		this.dadosCadastro = dadosCadastro;
	}

	@Então("^devo receber uma Excecao Negocio exception\\$\\.$")
	public void devo_receber_uma_Excecao_Negocio_exception$() throws Exception {
		dadosCadastro.forEach(cmd -> {
			try {
				protocoloServico.cadastrarProtocolo(cmd);
			} catch (ExcecaoNegocio e) {
				assertEquals(e.getMessage(), cmd.getMsgEsperada());
			}
		});
	}
}
