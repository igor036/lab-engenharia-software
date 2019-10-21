/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.dto.PaginacaoDto;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.cmd.PedidoProtocoloCmd;
import com.linecode.protocolo.dao.consultapaginada.ListaProtocoloConsultaPaginada;
import com.linecode.protocolo.dao.mapeadorlinha.ProtocoloMapeadorLinha;
import com.linecode.protocolo.dto.DetalheProtocoloDto;
import com.linecode.protocolo.dto.ListagemProtocoloDto;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;

@Repository
@PropertySource("com/linecode/protocolo/dao/ProtocoloDao.xml")
public class ProtocoloDao {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public long cadastrarProtocolo(CadastroProtocoloCmd cmd, long matriculaDocente, long idStatus) {
        return jdbcTemplate.queryForObject(
                env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.cadastrarProtocolo"), Long.class,
                cmd.getJustificativa(), cmd.getResumoPt(), cmd.getResumoEn(), cmd.getDataInicio(), cmd.getDataFim(),
                matriculaDocente, idStatus);
    }

    @Transactional
    public long cadastrarPedidoProtocolo(PedidoProtocoloCmd cmd) {
        return jdbcTemplate.queryForObject(
                env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.cadastrarPedidoProtocolo"), Long.class,
                cmd.getQuantidade(), cmd.getIdBioterio(), cmd.getIdBioterio(), cmd.getIdProtocolo());
    }

    @Transactional(readOnly = true)
    public PaginacaoDto<ListagemProtocoloDto> getListaProtocoloDocenteLogado(ConsultaListaProtocoloFiltro filtro,
            int paginaAtual, int qtdRegistrosPagina) {

        ListaProtocoloConsultaPaginada consultaPaginada = new ListaProtocoloConsultaPaginada(
                env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.getListaProtocoloDocenteLogado"), jdbcTemplate,
                filtro, paginaAtual, qtdRegistrosPagina);

        return consultaPaginada.getPaginacao();
    }
    
    @Transactional(readOnly = true)
    public DetalheProtocoloDto getDetalheProtocolo(long idProtocolo) {
        return jdbcTemplate.query(env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.getDetalheProtocolo"),
                ProtocoloMapeadorLinha.getDetalheProtocoloDtoMapeadorLinha(), idProtocolo);
    }
}
