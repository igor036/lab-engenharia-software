package com.linecode.ddd.protocolo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.linecode.protocolo.cmd.AvaliarProtocoloCmd;
import com.linecode.protocolo.cmd.CadastrarAvaliadorProtocoloCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.cmd.PedidoProtocoloCmd;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;

public class ProtocoloServicoDataProviderUnitTest {

	private static final String MSG_INFORMAR_JUSTIFICATIVA = "Informe a justificativa.";
	private static final String MSG_INFORMAR_RESUMO_PT = "Informe o resumo em português.";
	private static final String MSG_INFORMAR_RESUMO_EN = "Informe o resumo em inglês.";
	private static final String MSG_INFORMAR_LISTA_PEDIDO = "Informe ao menos um pedido";
	private static final String MSG_CODIGO_PROTOCOLO_INVALIDO = "Código do protocolo inválido.";
	private static final String MSG_CODIGO_STATUS_INVALIDO = "Código do status inválido.";
	private static final String MSG_INFORMAR_DESCRICAO_PARECER = "Informe a observação.";

	private static final String STRING_VAZIA = "";
	private static final String STRING_ESPACO_EM_BRANCO = "  ";
	private static final String STRING_VALIDA = "abc";
	private static final LocalDate DATA_INICIO = LocalDate.now().plusDays(1);
	private static final LocalDate DATA_FIM = LocalDate.now().plusDays(2);

	@DataProvider(name = "testCadastrarProtocoloDataProvider")
	public static Object[][] testCadastrarProtocoloDataProvider() {

		CadastroProtocoloCmd justificativaNula = new CadastroProtocoloCmd(null, STRING_VALIDA, STRING_VALIDA,
				DATA_INICIO, DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd justificativaVazia = new CadastroProtocoloCmd(STRING_VAZIA, STRING_VALIDA, STRING_VALIDA,
				DATA_INICIO, DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd justificativaEspacoEmBranco = new CadastroProtocoloCmd(STRING_ESPACO_EM_BRANCO,
				STRING_VALIDA, STRING_VALIDA, DATA_INICIO, DATA_FIM, getListaPedidoValida());

		CadastroProtocoloCmd resumoPtNulo = new CadastroProtocoloCmd(STRING_VALIDA, null, STRING_VALIDA, DATA_INICIO,
				DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd resumoPtVazio = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VAZIA, STRING_VALIDA,
				DATA_INICIO, DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd resumoPtEspacoEmBranco = new CadastroProtocoloCmd(STRING_VALIDA, STRING_ESPACO_EM_BRANCO,
				STRING_VALIDA, DATA_INICIO, DATA_FIM, getListaPedidoValida());

		CadastroProtocoloCmd resumoEnNulo = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, null, DATA_INICIO,
				DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd resumoEnVazio = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, STRING_VAZIA,
				DATA_INICIO, DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd resumoEnEspacoEmBranco = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_ESPACO_EM_BRANCO, DATA_INICIO, DATA_FIM, getListaPedidoValida());

		CadastroProtocoloCmd dataInicioNula = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, STRING_VALIDA,
				null, DATA_FIM, getListaPedidoValida());
		CadastroProtocoloCmd dataFiNula = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, STRING_VALIDA,
				DATA_INICIO, null, getListaPedidoValida());
		CadastroProtocoloCmd dataInicioMaiorDataFim = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_VALIDA, DATA_INICIO, LocalDate.now(), getListaPedidoValida());
		CadastroProtocoloCmd dataInicioMenorDataHoje = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_VALIDA, LocalDate.now().minusDays(1), DATA_FIM, getListaPedidoValida());

		CadastroProtocoloCmd listaPedidoaNula = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, STRING_VALIDA,
				DATA_INICIO, DATA_FIM, null);
		CadastroProtocoloCmd listaVazia = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA, STRING_VALIDA,
				DATA_INICIO, DATA_FIM, Collections.emptyList());

		CadastroProtocoloCmd pedidoIdEspecieInvalido = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_VALIDA, DATA_INICIO, DATA_FIM, Arrays.asList(new PedidoProtocoloCmd(0L, 1, 1L)));
		CadastroProtocoloCmd pedidoQuantidadeInvalida = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_VALIDA, DATA_INICIO, DATA_FIM, Arrays.asList(new PedidoProtocoloCmd(1L, 0, 1L)));
		CadastroProtocoloCmd pedidoIdBioterioInvalido = new CadastroProtocoloCmd(STRING_VALIDA, STRING_VALIDA,
				STRING_VALIDA, DATA_INICIO, DATA_FIM, Arrays.asList(new PedidoProtocoloCmd(1L, 1, 0L)));

		// @formatter:off
		return new Object[][] { 
			{ null, "Informe os dados do protocolo" },
			{justificativaNula, MSG_INFORMAR_JUSTIFICATIVA},
			{justificativaVazia, MSG_INFORMAR_JUSTIFICATIVA},
			{justificativaEspacoEmBranco, MSG_INFORMAR_JUSTIFICATIVA},
			{resumoPtNulo, MSG_INFORMAR_RESUMO_PT},
			{resumoPtVazio, MSG_INFORMAR_RESUMO_PT},
			{resumoPtEspacoEmBranco, MSG_INFORMAR_RESUMO_PT},
			{resumoEnNulo, MSG_INFORMAR_RESUMO_EN},
			{resumoEnVazio, MSG_INFORMAR_RESUMO_EN},
			{resumoEnEspacoEmBranco, MSG_INFORMAR_RESUMO_EN},
			{dataInicioNula, "Informe a data de inicio."},
			{dataFiNula, "Informe a data fim."},
			{dataInicioMaiorDataFim, "Período de tempo inválido. A data fim deve ser maior que a data início."},
			{dataInicioMenorDataHoje, "A data de início deve ser maior que a data atual."},
			{listaPedidoaNula, MSG_INFORMAR_LISTA_PEDIDO},
			{listaVazia, MSG_INFORMAR_LISTA_PEDIDO},
			{pedidoIdEspecieInvalido, "Id espécie inválido."},
			{pedidoQuantidadeInvalida, "Quantidade espécie inválida."},
			{pedidoIdBioterioInvalido, "Id biotério inválido."}
		}; 
		// @formatter:on
	}

	@DataProvider(name = "testGetListaProtocoloDataProvider")
	public static Object[][] testGetListaProtocoloDataProvider() {
		// @formatter:off
		TipoConsultaListaProtocoloEnumerador tipoIdProtocolo = TipoConsultaListaProtocoloEnumerador.CODIGO;
		TipoConsultaListaProtocoloEnumerador tipoStatus = TipoConsultaListaProtocoloEnumerador.STATUS;
		
		ConsultaListaProtocoloFiltro tipoNulo = new ConsultaListaProtocoloFiltro(null, null, 1L, 1L);
		ConsultaListaProtocoloFiltro idProtocoloNulo = new ConsultaListaProtocoloFiltro(tipoIdProtocolo, null, 1L, null);
		ConsultaListaProtocoloFiltro idProtocoloInvalido = new ConsultaListaProtocoloFiltro(tipoIdProtocolo, null, 1L, 0L);
		ConsultaListaProtocoloFiltro idStatusNullo = new ConsultaListaProtocoloFiltro(tipoStatus, null, null, 1L);
		ConsultaListaProtocoloFiltro idStatusInvalido = new ConsultaListaProtocoloFiltro(tipoStatus, null, 0L, 1L);
		
		
		return new Object[][] {
			{null, "Informe os dados da consulta!"},
			{tipoNulo, "Informe o tipo de consulta."},
			{idProtocoloNulo, MSG_CODIGO_PROTOCOLO_INVALIDO},
			{idProtocoloInvalido, MSG_CODIGO_PROTOCOLO_INVALIDO},
			{idStatusNullo, MSG_CODIGO_STATUS_INVALIDO},
			{idStatusInvalido, MSG_CODIGO_STATUS_INVALIDO}
		};
		// @formatter:on
	}

	@DataProvider(name = "testCadastrarAvaliadorProtocoloDataProvider")
	public static Object[][] testCadastrarAvaliadorProtocoloDataProvider() {

		CadastrarAvaliadorProtocoloCmd idAvaliadorInvalido = new CadastrarAvaliadorProtocoloCmd(0L, 1L);
		CadastrarAvaliadorProtocoloCmd idProtocoloInvalido = new CadastrarAvaliadorProtocoloCmd(1L, 0L);

		// @formatter:off
		return new Object[][] {
			{null, "Informe os dados do cadastro de avaliador!"},
			{idAvaliadorInvalido, "ID do avaliador inválido."},
			{idProtocoloInvalido, "ID do protocolo inválido."}
		};
		// @formatter:on
	}

	@DataProvider(name = "testCadastrarAvaliadorProtocoloAutorDataProvider")
	public static Object[][] testCadastrarAvaliadorProtocoloAutorDataProvider() {
		return new Object[][] { { new CadastrarAvaliadorProtocoloCmd(1L, 1L) } };
	}

	@DataProvider(name = "testAvaliarProtocoloDataProvider")
	public static Object[][] testAvaliarProtocoloDataProvider() {

		AvaliarProtocoloCmd descricaoNula = new AvaliarProtocoloCmd(true, null, 1L);
		AvaliarProtocoloCmd descricaoVazia = new AvaliarProtocoloCmd(true, STRING_VAZIA, 1L);
		AvaliarProtocoloCmd descricaoEspacoEmBranco = new AvaliarProtocoloCmd(true, STRING_ESPACO_EM_BRANCO, 1L);
		AvaliarProtocoloCmd idProtocoloInvalido = new AvaliarProtocoloCmd(true, STRING_VALIDA, 0L);

		// @formatter:off
		return new Object[][] {
			{null, "Informe os dados da avaliação!"},
			{descricaoNula, MSG_INFORMAR_DESCRICAO_PARECER},
			{descricaoVazia, MSG_INFORMAR_DESCRICAO_PARECER},
			{descricaoEspacoEmBranco, MSG_INFORMAR_DESCRICAO_PARECER},
			{idProtocoloInvalido, "ID do protocolo inválido."}
		};
		// @formatter:on

	}

	private static List<PedidoProtocoloCmd> getListaPedidoValida() {
		return Arrays.asList(new PedidoProtocoloCmd(1L, 1, 1L));
	}
}
