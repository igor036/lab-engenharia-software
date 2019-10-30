package com.linecode.bdd.docente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;

import com.linecode.bdd.docente.cmd.CadastroDocenteTestCmd;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.configuracao.ContextoAplicacao;
import com.linecode.docente.servico.DocenteServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class CadastroDocenteStepsTest {
	
    private ContextoAplicacao contextoAplicacao = new ContextoAplicacao();
    
	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	@Spy
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	private List<CadastroDocenteTestCmd> dadosCadastro;
	
	public CadastroDocenteStepsTest() {
        MockitoAnnotations.initMocks(this);
        ApplicationContext applicationContextMock = mock(ApplicationContext.class);
        contextoAplicacao.setApplicationContext(applicationContextMock);
    }
	
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
