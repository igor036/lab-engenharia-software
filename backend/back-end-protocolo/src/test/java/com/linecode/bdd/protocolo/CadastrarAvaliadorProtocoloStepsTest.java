package com.linecode.bdd.protocolo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;

import com.linecode.bdd.protocolo.cmd.CadastrarAvaliadorProtocoloTestCmd;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.protocolo.servico.ProtocoloServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CadastrarAvaliadorProtocoloStepsTest {
	
	private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();

	@InjectMocks
	private ProtocoloServico protocoloServico = new ProtocoloServico();

	@Spy
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	private List<CadastrarAvaliadorProtocoloTestCmd> dadosCadastro;
	
	public CadastrarAvaliadorProtocoloStepsTest() {
		MockitoAnnotations.initMocks(this);
		ApplicationContext applicationContextMock = mock(ApplicationContext.class);
		contextoAplicacao.setApplicationContext(applicationContextMock);
	}

	@Quando("^desejar efetuar o cadastro de um determinado avaliador para um protocolo informando um cadastro invalido com os seguintes dados$")
	public void desejar_efetuar_o_cadastro_de_um_determinado_avaliador_para_um_protocolo_informando_um_cadastro_invalido_com_os_seguintes_dados(List<CadastrarAvaliadorProtocoloTestCmd> dadosCadastro) throws Exception {
		this.dadosCadastro = dadosCadastro;
	}

	@Então("^devo receber uma Excecao informando que o id do avaliador ou o id do protocolo estão inválidos exception\\$\\.$")
	public void devo_receber_uma_Excecao_informando_que_o_id_do_avaliador_ou_o_id_do_protocolo_estão_inválidos_exception$() throws Exception {
		dadosCadastro.forEach(cadastro -> {
			try {
				protocoloServico.cadastrarAvaliadorProtocolo(cadastro);
			} catch (ExcecaoNegocio e) {
				assertEquals(e.getMessage(), cadastro.getMsgEsperada());
			}
		});
	}
}
