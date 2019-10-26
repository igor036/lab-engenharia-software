/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 19/10/2019
 */
package com.linecode.compartilhado.dao.mapeadorlinha;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linecode.compartilhado.dto.OpcaoDto;

public class OpcaoDtoMapeadorLinha implements RowMapper<OpcaoDto<Long>>  {

	@Override
	public OpcaoDto<Long> mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new OpcaoDto<>(rs.getLong("COLUNA_ID"), rs.getString("COLUNA_DESCRICAO"));
	}
}
