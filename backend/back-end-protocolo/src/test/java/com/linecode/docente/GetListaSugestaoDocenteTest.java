package com.linecode.docente;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;

import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.servico.DocenteServico;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class GetListaSugestaoDocenteTest {

	@InjectMocks
	private DocenteServico docenteServico = new DocenteServico();
	
	private List<Map<String, String>> dados;
	
	@Quando("^consultar a lista de sugestão informando um nome vazio com os seguintes dados$")
	public void consultar_a_lista_de_sugestão_informando_um_nome_vazio_com_os_seguintes_dados(
			List<Map<String, String>> dados) throws Exception {
		this.dados = dados;
	}

	@Então("^devo recer uma excecao solicitando para informar o nome\\.$")
	public void devo_recer_uma_excecao_solicitando_para_informar_o_nome() throws Exception {
		this.dados.forEach(linha -> {
			try {
				docenteServico.getListaSugestaoDocente(linha.get("nome"));
			} catch (ExcecaoNegocio e) {
				assertEquals(e.getMessage(), linha.get("MsgEsperada"));
			}
		});
	}
}
