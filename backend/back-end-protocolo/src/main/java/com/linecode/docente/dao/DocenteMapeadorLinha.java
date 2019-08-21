/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.linecode.docente.dto.DocenteDto;
import com.linecode.publico.enumerador.PerfilEnumerador;

public class DocenteMapeadorLinha {

	private DocenteMapeadorLinha() {
		//somente metodos estaticos
	}
	
	public static DocenteDtoMapeadorLinha getDocenteDtoMapeadorLinha() {
		return new DocenteDtoMapeadorLinha();
	}
	
	private static class DocenteDtoMapeadorLinha implements ResultSetExtractor<DocenteDto> {

		@Override
		public DocenteDto extractData(ResultSet rs) throws SQLException {

			if (rs.next()) {
				return new DocenteDto(rs.getLong("MATRICULA"), rs.getString("NOME"), rs.getString("EMAIL"),
						PerfilEnumerador.getPerfil(rs.getString("PERFIL")));
			}

			return null;
		}

	}
}
