/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linecode.util.dto.PerfilDto;

public class UtilMapeadorLinha {

    private UtilMapeadorLinha () {
        //somente metodos estaticos
    }
    
    public static PerfilDtoMapeadorLinha getPerfilDtoMapeadorLinha() {
        return new PerfilDtoMapeadorLinha();
    }
    
    private static class PerfilDtoMapeadorLinha implements RowMapper<PerfilDto> {

        @Override
        public PerfilDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PerfilDto(rs.getLong("ID_ROLE"), rs.getString("NOME"));
        }
        
    }
}
