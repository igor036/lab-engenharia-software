package com.linecode.protocolo.dao.consultapaginada;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;

import com.linecode.compartilhado.dao.consultapaginada.ConsultaPaginadaDao;
import com.linecode.protocolo.dto.ListagemProtocoloDto;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;

public class ListaProtocoloConsultaPaginada
        extends ConsultaPaginadaDao<ConsultaListaProtocoloFiltro, ListagemProtocoloDto> {

    public ListaProtocoloConsultaPaginada(String sqlBase, JdbcTemplate jdbcTemplate,
            ConsultaListaProtocoloFiltro filtro, int paginaAtual, int qtdRegistrosPagina) {
        super(sqlBase, jdbcTemplate, filtro, paginaAtual, qtdRegistrosPagina);
    }

    @Override
    protected String getSqlFiltroAdicional() {

        adicionarParametro(filtro.getIdDocente(), Types.INTEGER);
        StringBuilder sqlFiltro = new StringBuilder();
        sqlFiltro.append(" AND P.FK_MATRICULA = ? ");
        
        if (filtro.getTipo().isConsultaCodigo()) {
            adicionarParametro(filtro.getIdProtocolo(), Types.INTEGER);
            sqlFiltro.append(" AND P.ID_PROTOCOLO = ? ");
        } else if (filtro.getTipo().isConsultaStatus()) {
            adicionarParametro(filtro.getIdStatus(), Types.BIGINT);
            sqlFiltro.append(" AND S.ID_STATUS = ? ");
        }
        
        return sqlFiltro.toString();
    }

    @Override
    protected ListagemProtocoloDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ListagemProtocoloDto(rs.getLong("ID"), rs.getString("NOME_DOCENTE"), rs.getString("DATA_INICIO"),
                rs.getString("DATA_FIM"));
    }
}
