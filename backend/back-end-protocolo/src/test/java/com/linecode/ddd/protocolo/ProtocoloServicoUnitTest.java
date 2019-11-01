package com.linecode.ddd.protocolo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;
import com.linecode.protocolo.cmd.AvaliarProtocoloCmd;
import com.linecode.protocolo.cmd.CadastrarAvaliadorProtocoloCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.dao.ProtocoloDao;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;
import com.linecode.protocolo.servico.ProtocoloServico;
import com.linecode.util.servico.UtilServico;

public class ProtocoloServicoUnitTest {

	private static final String MSG_EXCECAO_NAO_LANCADA = "Era pra ter lançado uma exceção.";
	
	private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();

    @InjectMocks
    private ProtocoloServico protocoloServico = new ProtocoloServico();
    
    @Mock(name = "utilServico")
    private UtilServico utilServicoMock;
    
    @Mock(name = "protocoloDao")
    private ProtocoloDao protocoloDaoMock;
    
    @Mock(name = "docenteServico")
    private DocenteServico docenteServicoMock;
    
    @Spy
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    @BeforeClass
    public void configurarContexto() {
        MockitoAnnotations.initMocks(this);
        ApplicationContext applicationContextMock = mock(ApplicationContext.class);
        contextoAplicacao.setApplicationContext(applicationContextMock);
    }
    
    @Test(dataProvider = "testCadastrarProtocoloDataProvider", dataProviderClass = ProtocoloServicoDataProviderUnitTest.class)
    public void testCadastrarProtocolo(CadastroProtocoloCmd cmd, String msgEsperada) {
    	
    	when(docenteServicoMock.getDadosDocenteLogado()).thenReturn(new DocenteDto(1L, null, null, null));
    	when(protocoloDaoMock.cadastrarProtocolo(cmd, 1L, 1L)).thenReturn(1L);
    	when(utilServicoMock.getIdStatusInicialProtocolo()).thenReturn(1L);
    	
    	try {
    		protocoloServico.cadastrarProtocolo(cmd);
    		fail(MSG_EXCECAO_NAO_LANCADA);
    	} catch (IllegalArgumentException | ExcecaoNegocio e) {
			assertEquals(e.getMessage(), msgEsperada);
		}
    }
    
    @Test(dataProvider = "testGetListaProtocoloDataProvider", dataProviderClass = ProtocoloServicoDataProviderUnitTest.class)
    public void testGetListaProtocolo(ConsultaListaProtocoloFiltro filtro, String msgEsperada) {
    	try {
    		protocoloServico.getListaProtocolo(filtro, 1, 1);
    		fail(MSG_EXCECAO_NAO_LANCADA);
    	} catch (ExcecaoNegocio | IllegalArgumentException e) {
			assertEquals(e.getMessage(), msgEsperada);
		}
    }
    
    @Test(dataProvider = "testCadastrarAvaliadorProtocoloDataProvider", dataProviderClass = ProtocoloServicoDataProviderUnitTest.class)
    public void testCadastrarAvaliadorProtocolo(CadastrarAvaliadorProtocoloCmd cmd, String msgEsperada) {
    	try {
    		protocoloServico.cadastrarAvaliadorProtocolo(cmd);
    		fail(MSG_EXCECAO_NAO_LANCADA);
    	} catch (ExcecaoNegocio | IllegalArgumentException e) {
			assertEquals(e.getMessage(), msgEsperada);
		}
    }
    
    @Test(dataProvider = "testCadastrarAvaliadorProtocoloAutorDataProvider", dataProviderClass = ProtocoloServicoDataProviderUnitTest.class, expectedExceptions = ExcecaoNegocio.class, expectedExceptionsMessageRegExp = "O doscente não pode avaliar seu próprio protocolo.")
    public void testCadastrarAvaliadorProtocoloAutor(CadastrarAvaliadorProtocoloCmd cmd) {
    	when(protocoloDaoMock.isEmissorProtocolo(cmd.getIdProtocolo(), cmd.getIdAvaliador())).thenReturn(true);
    	protocoloServico.cadastrarAvaliadorProtocolo(cmd);
    }
    
    @Test(dataProvider = "testAvaliarProtocoloDataProvider", dataProviderClass = ProtocoloServicoDataProviderUnitTest.class)
    public void testAvaliarProtocolo(AvaliarProtocoloCmd cmd, String msgEsperada) {
    	try {
    		protocoloServico.avaliarProtocolo(cmd);
    		fail(MSG_EXCECAO_NAO_LANCADA);
    	} catch (ExcecaoNegocio | IllegalArgumentException e) {
    		assertEquals(e.getMessage(), msgEsperada);
		}
    }
}
