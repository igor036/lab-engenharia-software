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
        return new StringBuilder()
                .append(adicionarParametroConsultaDocenteLogado())
                .append(adicionarParametroConsultaAvaliar())
                .append(adicionarParametroConsultaOutrosDocentes())
                .append(adicionarParametroConsultaCodigo())
                .append(adicionarParametroConsultaStatus())
                .toString();
    }

    @Override
    protected ListagemProtocoloDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ListagemProtocoloDto(rs.getLong("ID"), rs.getString("NOME_DOCENTE"), rs.getString("DATA_INICIO"),
                rs.getString("DATA_FIM"), rs.getString("STATUS"));
    }

    private String adicionarParametroConsultaDocenteLogado() {
        if (filtro.getCategoria().isDocenteLogado()) {
            adicionarParametro(filtro.getIdDocente(), Types.INTEGER);
            return " AND P.FK_MATRICULA = ? ";
        }
        return "";
    }

    private String adicionarParametroConsultaAvaliar() {
        if (filtro.getCategoria().isAvaliar()) {
            adicionarParametro(filtro.getIdDocente(), Types.INTEGER);
            return "EXISTS (SELECT 1 FROM TAB_PARECER PR WHERE  PR.FK_ID_PROTOCOLO = P.ID_PROTOCOLO AND PR.FK_MATRICULA = ?) ";
        }
        return "";
    }

    private String adicionarParametroConsultaOutrosDocentes() {
        if (filtro.getCategoria().isOutrosDocentes()) {
            adicionarParametro(filtro.getIdDocente(), Types.INTEGER);
            return " AND P.FK_MATRICULA <> ? ";
        }
        return "";
    }

    private String adicionarParametroConsultaCodigo() {
        if (filtro.getTipo().isConsultaCodigo()) {
            adicionarParametro(filtro.getIdProtocolo(), Types.INTEGER);
            return " AND P.ID_PROTOCOLO = ? ";
        }
        return "";
    }

    private String adicionarParametroConsultaStatus() {
        if (filtro.getTipo().isConsultaStatus()) {
            adicionarParametro(filtro.getIdStatus(), Types.BIGINT);
            return " AND S.ID_STATUS = ? ";
        }
        return "";
    }
}
