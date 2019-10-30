/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 30/10/2019
 */
package com.linecode.ddd.docente;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.servico.DocenteServico;

public class DocenteServicoUnitTest {
	
	private static final String MSG_EXCECAO_NAO_LANCADA = "Era pra ter lançado uma exceção.";
	
	private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();
	
	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	@Spy
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@BeforeClass
	public void configurarContexto() {
        MockitoAnnotations.initMocks(this);
        ApplicationContext applicationContextMock = mock(ApplicationContext.class);
        contextoAplicacao.setApplicationContext(applicationContextMock);
    }
	
	@Test(dataProvider = "testGetDocentePorEmailSenhaDataProvider", dataProviderClass = DocenteServicoUnitDataProvider.class)
	public void testGetDocentePorEmailSenha(String email, String senha, String msgEsperada) {
		try {
			docenteServico.getDocentePorEmailSenha(email, senha);
			fail(MSG_EXCECAO_NAO_LANCADA);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), msgEsperada);
		}
	}
	
	@Test(dataProvider = "testCadastrarDocenteDataProvider", dataProviderClass = DocenteServicoUnitDataProvider.class)
	public void testCadastrarDocente(CadastroDocenteCmd cmd, String msgEsperada) {
		try {
			docenteServico.cadastrarDocente(cmd);
			fail(MSG_EXCECAO_NAO_LANCADA);
		} catch (IllegalArgumentException | ExcecaoNegocio e) {
			assertTrue(msgEsperada.contains(e.getMessage()));
		}
	}
	
	@Test(dataProvider = "testGetListaSugestaoDocenteDataProvider", dataProviderClass = DocenteServicoUnitDataProvider.class, expectedExceptions = ExcecaoNegocio.class, expectedExceptionsMessageRegExp = "Informe o nome da consulta.")
	public void testGetListaSugestaoDocente(String nome) {
		docenteServico.getListaSugestaoDocente(nome);
	}
}
