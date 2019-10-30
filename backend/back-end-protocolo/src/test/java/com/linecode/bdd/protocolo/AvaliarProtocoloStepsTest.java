package com.linecode.bdd.protocolo;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;

import com.linecode.bdd.protocolo.cmd.AvaliarProtocoloCmdTest;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.protocolo.servico.ProtocoloServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AvaliarProtocoloStepsTest {

	private List<AvaliarProtocoloCmdTest> dadosAvaliacao;
	private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();

	@InjectMocks
	private ProtocoloServico protocoloServico = new ProtocoloServico();

	@Spy
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public AvaliarProtocoloStepsTest() {
		MockitoAnnotations.initMocks(this);
		ApplicationContext applicationContextMock = mock(ApplicationContext.class);
		contextoAplicacao.setApplicationContext(applicationContextMock);
	}

	@Quando("^o doscente desejar avaliar um protocolo informando uma avaliacao invalida com os seguintes dados$")
	public void o_doscente_desejar_avaliar_um_protocolo_informando_uma_avaliacao_invalida_com_os_seguintes_dados(
			List<AvaliarProtocoloCmdTest> dadosAvaliacao) throws Exception {
		this.dadosAvaliacao = dadosAvaliacao;
	}

	@Então("^devo receber uma Excecao informando que os dados da avliacao estao invalidos exception\\$\\.$")
	public void devo_receber_uma_Excecao_informando_que_os_dados_da_avliacao_estao_invalidos_exception$()
			throws Exception {
		dadosAvaliacao.forEach(avaliacao -> {
			try {
				protocoloServico.avaliarProtocolo(avaliacao);
			} catch (ExcecaoNegocio e) {
				assertEquals(e.getMessage(), avaliacao.getMsgEsperada());
			}
		});
	}
}
