package com.linecode.bdd.docente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;

import com.linecode.docente.servico.DocenteServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class LoginDocenteStepsTest {
	
	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	private List<Map<String, String>> dadosLogin;
	
	@Quando("^eu desejar logar no sistema informando os seguintes dados$")
	public void eu_desejar_logar_no_sistema_informando_os_seguintes_dados(List<Map<String, String>> dadosLogin) throws Exception {
		this.dadosLogin = dadosLogin;
	}

	@Então("^devo recer um Illegal Argument exception\\$\\.$")
	public void devo_recer_um_Illegal_Argument_exception$() throws Exception {
		this.dadosLogin.forEach( mapa -> {
			try {
				docenteServico.getDocentePorEmailSenha(mapa.get("Email"), mapa.get("Senha"));
				fail("Era pra ter lançado uma exceção de argumento inválido.");
			} catch (IllegalArgumentException e) {
				assertEquals(e.getMessage(), mapa.get("MsgEsperada"));
			}
		});
	}
}
