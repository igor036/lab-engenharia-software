/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linecode.util.dto.OpcaoDto;

public class UtilMapeadorLinha {

    private UtilMapeadorLinha () {
        //somente metodos estaticos
    }
    
    public static OpcaoPerfilDtoMapeadorLinha getOpcaoPerfilDtoMapeadorLinha() {
        return new OpcaoPerfilDtoMapeadorLinha();
    }
    
    private static class OpcaoPerfilDtoMapeadorLinha implements RowMapper<OpcaoDto<Long>> {
        @Override
        public OpcaoDto<Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OpcaoDto<>(rs.getLong("ID_ROLE"), rs.getString("NOME"));
        }
    }
}
