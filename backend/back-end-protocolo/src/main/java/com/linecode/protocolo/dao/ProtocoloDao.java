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

import com.linecode.protocolo.cmd.CadastroProtocoloCmd;

@Repository
@PropertySource("com/linecode/protocolo/dao/ProtocoloDao.xml")
public class ProtocoloDao {

	@Autowired
	private Environment env;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean cadastrarProtocolo(CadastroProtocoloCmd cmd, long matriculaDocente) {
		return jdbcTemplate.update(env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.cadastrarProtocolo"),
				cmd.getJustificativa(), cmd.getResumoPt(), cmd.getResumoEn(), cmd.getDataInicio(), cmd.getDataFim(),
				matriculaDocente) == 1;
	}
}
