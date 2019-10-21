package com.linecode.protocolo.dao.consultapaginada;

import java.sql.ResultSet;
import java.sql.SQLException;

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

        if (filtro.getTipo().isConsultaTodos()) {
            return "";
        }

        adicionarParametro(filtro.getIdProtocolo());
        adicionarParametro(filtro.getIdDocente());

        return " AND P.ID_PROTOCOLO = ? AND P.FK_MATRICULA = ?";
    }

    @Override
    protected ListagemProtocoloDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ListagemProtocoloDto(rs.getLong("ID"), rs.getString("NOME_DOCENTE"), rs.getString("DATA_INICIO"),
                rs.getString("DATA_FIM"));
    }

}
