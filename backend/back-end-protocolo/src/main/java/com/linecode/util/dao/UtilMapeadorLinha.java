/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linecode.compartilhado.dto.OpcaoDto;

public class UtilMapeadorLinha {

	private static final String NM_PROPRIEDADE_NOME = "NOME";
	
    private UtilMapeadorLinha () {
        //somente metodos estaticos
    }
    
    public static OpcaoPerfilDtoMapeadorLinha getOpcaoPerfilDtoMapeadorLinha() {
        return new OpcaoPerfilDtoMapeadorLinha();
    }
    
    public static OpcaoEspecieDtoMapeadorLinha getOpcaoEspecieDtoMapeadorLinha() {
    	return new OpcaoEspecieDtoMapeadorLinha();
    }
    
    public static OpcaoBioterioMapeadorLinha getOpcaoBioterioMapeadorLinha() {
    	return new OpcaoBioterioMapeadorLinha();
    }
    
    private static class OpcaoPerfilDtoMapeadorLinha implements RowMapper<OpcaoDto<Long>> {
        @Override
        public OpcaoDto<Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OpcaoDto<>(rs.getLong("ID_ROLE"), rs.getString(NM_PROPRIEDADE_NOME));
        }
    }
    
    private static class OpcaoEspecieDtoMapeadorLinha implements RowMapper<OpcaoDto<Long>> {
		@Override
		public OpcaoDto<Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new OpcaoDto<Long>(rs.getLong("ID_ESPECIE"), rs.getString(NM_PROPRIEDADE_NOME));
		}	
    }
    
    private static class OpcaoBioterioMapeadorLinha implements RowMapper<OpcaoDto<Long>> {
		@Override
		public OpcaoDto<Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new OpcaoDto<Long>(rs.getLong("ID_BIOTERIO"), rs.getString(NM_PROPRIEDADE_NOME));
		}	
    }
}
