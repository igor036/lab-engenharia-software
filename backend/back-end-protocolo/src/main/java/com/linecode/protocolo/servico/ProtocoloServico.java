/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.servico;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.dto.PaginacaoDto;
import com.linecode.compartilhado.excecao.ExcecaoAplicacao;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.servico.DocenteServico;
import com.linecode.protocolo.cmd.AvaliarProtocoloCmd;
import com.linecode.protocolo.cmd.CadastrarAvaliadorProtocoloCmd;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.cmd.PedidoProtocoloCmd;
import com.linecode.protocolo.dao.ProtocoloDao;
import com.linecode.protocolo.dto.DetalheProtocoloDto;
import com.linecode.protocolo.dto.ListagemProtocoloDto;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;
import com.linecode.util.servico.UtilServico;

import io.jsonwebtoken.lang.Assert;

@Service
public class ProtocoloServico {

	@Autowired
	private ProtocoloDao protocoloDao;

	@Autowired
	private DocenteServico docenteServico;

	@Autowired
	private UtilServico utilServico;

	@Autowired
	private Validator validator;

	@Autowired
	private Environment env;

	/**
	 * Efetua o cadastro de um protocolo e o vincula ao docente logado
	 * 
	 * A data fim deve ser obrigatóriamente maior que a data de início.
	 * 
	 * Metodo permitido somente para <b>PerfilEnumerador.PROFESSOR</b>
	 * 
	 * @param cmd - dados do protocolo {@link CadastroProtocoloCmd}
	 */
	@Transactional
	@PreAuthorize("@autorizacaoServico.isAutorizado('PROFESSOR')")
	public void cadastrarProtocolo(CadastroProtocoloCmd cmd) {

		Assert.notNull(cmd, "Informe os dados do protocolo");

		Set<ConstraintViolation<CadastroProtocoloCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {

			if (LocalDate.now().compareTo(cmd.getDataInicio()) >= 0) {
				throw new ExcecaoNegocio("A data de início deve ser maior que a data atual.");
			}

			if (cmd.getDataInicio().compareTo(cmd.getDataFim()) >= 0) {
				throw new ExcecaoNegocio("Período de tempo inválido. A data fim deve ser maior que a data início.");
			}

			long idStatusInicial = utilServico.getIdStatusInicialProtocolo();
			long idProtocolo = protocoloDao.cadastrarProtocolo(cmd,
					docenteServico.getDadosDocenteLogado().getMatricula(), idStatusInicial);

			if (idProtocolo > 0) {
				cmd.getLitaPedidoProtocolo().forEach(pedido -> cadastrarPedidoProtocolo(pedido, idProtocolo));
			} else {
				throw new ExcecaoAplicacao("Houve um erro ao cadastrar o protocolo.", null);
			}

		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Retorna uma consulta de protocolo.
	 * 
	 * @param filtro             - dados do filtro da consulta
	 *                           {@link ConsultaListaProtocoloFiltro}
	 * @param paginaAtual        - pagina da consulta atual {@link Integer}
	 * @param qtdRegistrosPagina - quantidade de registros por pagina
	 *                           {@link Integer}
	 * @return paginacao da consulta {@link PaginacaoDto<ListagemProtocoloDto>}
	 */
	@PreAuthorize("@autorizacaoServico.isAutenticado()")
	public PaginacaoDto<ListagemProtocoloDto> getListaProtocolo(ConsultaListaProtocoloFiltro filtro, int paginaAtual,
			int qtdRegistrosPagina) {

		Assert.notNull(filtro, "Informe os dados da consulta!");

		Set<ConstraintViolation<ConsultaListaProtocoloFiltro>> violacoes = validator.validate(filtro);

		if (violacoes.isEmpty()) {

			if (filtro.getTipo().isConsultaCodigo()
					&& (filtro.getIdProtocolo() == null || filtro.getIdProtocolo() <= 0)) {
				throw new ExcecaoNegocio("Código do protocolo inválido.");
			}

			if (filtro.getTipo().isConsultaStatus() && (filtro.getIdStatus() == null || filtro.getIdStatus() <= 0)) {
				throw new ExcecaoNegocio("Código do status inválido.");
			}

			filtro.setIdDocente(docenteServico.getDadosDocenteLogado().getMatricula());

			return protocoloDao.getListaProtocolo(filtro, paginaAtual, qtdRegistrosPagina);

		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Consulta os dados detalhados de um determinado protocolo pelo ID.
	 * 
	 * @return dados {@link DetalheProtocoloDto}
	 */
	@PreAuthorize("@autorizacaoServico.isAutenticado()")
	public DetalheProtocoloDto getDetalheProtocolo(long idProtocolo) {
		return protocoloDao.getDetalheProtocolo(idProtocolo);
	}

	/**
	 * Efetua o cadastro de um determinado avaliador para um determinado protocolo.
	 * 
	 * @param cadastro - contem o id do avaliador e o id do protocolo
	 *                 {@link CadastrarAvaliadorProtocoloCmd}
	 */
	@Transactional
	@PreAuthorize("@autorizacaoServico.isAutorizacaoAdmin()")
	public void cadastrarAvaliadorProtocolo(CadastrarAvaliadorProtocoloCmd cmd) {
		
		Assert.notNull(cmd, "Informe os dados do cadastro de avaliador!");
		
		Set<ConstraintViolation<CadastrarAvaliadorProtocoloCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {

			if (protocoloDao.isEmissorProtocolo(cmd.getIdProtocolo(), cmd.getIdAvaliador())) {
				throw new ExcecaoNegocio("O doscente não pode avaliar seu próprio protocolo.");
			}

			protocoloDao.excluirAvaliadorProtocolo(cmd.getIdProtocolo());
			protocoloDao.cadastrarAvaliadorProtocolo(cmd);
			protocoloDao.atualizarStatusProtocolo(cmd.getIdProtocolo(),
					utilServico.getIdStatusPorDescricao(env.getProperty("protocolo.status.encaminhado")));
		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	@PreAuthorize("@autorizacaoServico.isAutenticado()")
	public void avaliarProtocolo(AvaliarProtocoloCmd avaliacao) {

		Assert.notNull(avaliacao, "Informe os dados da avaliação!");
		
		Set<ConstraintViolation<AvaliarProtocoloCmd>> violacoes = validator.validate(avaliacao);

		if (violacoes.isEmpty()) {
			long idDocenteLogado = docenteServico.getDadosDocenteLogado().getMatricula();
			if (protocoloDao.isAvaliadorProtocolo(avaliacao.getIdProtocolo(), idDocenteLogado)) {
				
				String descricaoStatus = avaliacao.isDeferido() ? 
						env.getProperty("protocolo.status.deferido") :
						env.getProperty("protocolo.status.indeferido");
				long idStatus = utilServico.getIdStatusPorDescricao(descricaoStatus);
				
				protocoloDao.atualizarStatusProtocolo(avaliacao.getIdProtocolo(), idStatus);
				protocoloDao.avaliarProtocolo(avaliacao);
				
			} else {
				throw new ExcecaoNegocio("Você não pode avaliar este protocolo.");
			}
		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}

	/**
	 * Efetua o cadastro de um determinado pedido para um protocolo
	 * 
	 * @param pedido      {@link PedidoProtocoloCmd} dados do pedido.
	 * @param idProtocolo {@link Long} id do protocolo.
	 */
	@Transactional
	private void cadastrarPedidoProtocolo(PedidoProtocoloCmd pedido, long idProtocolo) {

		Set<ConstraintViolation<PedidoProtocoloCmd>> violacoes = validator.validate(pedido);

		if (violacoes.isEmpty()) {
			pedido.setIdProtocolo(idProtocolo);
			protocoloDao.cadastrarPedidoProtocolo(pedido);
		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}
}
