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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.dto.PaginacaoDto;
import com.linecode.compartilhado.excecao.ExcecaoAplicacao;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.servico.DocenteServico;
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

    /**
     * Efetua o cadastro de um protocolo e o vincula ao docente logado
     * 
     * A data fim deve ser obrigatóriamente maior que a data de início.
     * 
     * @param cmd - dados do protocolo {@link CadastroProtocoloCmd}
     */
    @Transactional
    @PreAuthorize("@autorizacaoServico.isAutenticado()")
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
     * Retorna uma consulta de protocolo do docente <b>LOGADO</b> de forma paginada.
     * 
     * @param filtro             - dados do filtro da consulta {@link ConsultaListaProtocoloFiltro}
     * @param paginaAtual        - pagina da consulta atual {@link Integer}
     * @param qtdRegistrosPagina - quantidade de registros por pagina {@link Integer}
     * @return paginacao da consulta {@link PaginacaoDto<ListagemProtocoloDto>}
     */
    @PreAuthorize("@autorizacaoServico.isAutenticado()")
    public PaginacaoDto<ListagemProtocoloDto> getListaProtocoloDocenteLogado(ConsultaListaProtocoloFiltro filtro,
            int paginaAtual, int qtdRegistrosPagina) {

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

            return protocoloDao.getListaProtocoloDocenteLogado(filtro, paginaAtual, qtdRegistrosPagina);

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
