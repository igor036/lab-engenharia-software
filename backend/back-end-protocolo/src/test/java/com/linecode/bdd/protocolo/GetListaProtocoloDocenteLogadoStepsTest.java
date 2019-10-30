package com.linecode.bdd.protocolo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;

import com.linecode.bdd.protocolo.filtro.ConsultaListaProtocoloTestFiltro;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;
import com.linecode.protocolo.servico.ProtocoloServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class GetListaProtocoloDocenteLogadoStepsTest {

	private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();

	@InjectMocks
	private ProtocoloServico protocoloServico = new ProtocoloServico();

	@Spy
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private List<ConsultaListaProtocoloTestFiltro> filtros = new ArrayList<>();

	public GetListaProtocoloDocenteLogadoStepsTest() {
		MockitoAnnotations.initMocks(this);
		ApplicationContext applicationContextMock = mock(ApplicationContext.class);
		contextoAplicacao.setApplicationContext(applicationContextMock);
	}

	@Quando("^desejar efetuar uma consultla dos meus protocolos informando um filtro invalido com os seguintes dados$")
	public void desejar_efetuar_uma_consultla_dos_meus_protocolos_informando_um_filtro_invalido_com_os_seguintes_dados(
		List<Map<String, String>> dataTable) throws Exception {
		dataTable.forEach(mapa -> {
			filtros.add(mapaParaConsultaListaProtocoloTestFiltro(mapa));
		});
	}

	@Então("^devo receber uma Excecao Negocio\\.$")
	public void devo_receber_uma_Excecao_Negocio() throws Exception {
		this.filtros.forEach(filtro -> {
			try {
				protocoloServico.getListaProtocolo(filtro, 1, 5);
			} catch (ExcecaoNegocio e) {
				assertEquals(e.getMessage(), filtro.getMsgEsperada());
			}
		});
	}
	
	private ConsultaListaProtocoloTestFiltro mapaParaConsultaListaProtocoloTestFiltro(Map<String, String> mapa) {
		
		TipoConsultaListaProtocoloEnumerador tipo = TipoConsultaListaProtocoloEnumerador.forValue(mapa.get("tipo"));
		Long idStatus = Long.valueOf(mapa.get("idStatus"));
		Long idProtocolo = Long.valueOf(mapa.get("idProtocolo"));
		String msgEsperada = mapa.get("msgEsperada");
		
		return new ConsultaListaProtocoloTestFiltro(tipo, idStatus, idProtocolo, msgEsperada);
	}
}
