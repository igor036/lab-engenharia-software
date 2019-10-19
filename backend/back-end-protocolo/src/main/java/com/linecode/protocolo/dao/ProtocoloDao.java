/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.dao;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.servico.DataServico;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;

@Repository
@PropertySource("com/linecode/protocolo/dao/ProtocoloDao.xml")
public class ProtocoloDao {

	@Autowired
	private Environment env;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataServico dataServico;
	
	@Transactional
	public long cadastrarProtocolo(CadastroProtocoloCmd cmd, long matriculaDocente, long idStatus) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.cadastrarProtocolo");

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, cmd.getJustificativa());
			ps.setString(2, cmd.getResumoPt());
			ps.setString(3, cmd.getResumoEn());	
			ps.setDate(4, dataServico.localDateParaSqlDate(cmd.getDataInicio()));
			ps.setDate(4, dataServico.localDateParaSqlDate(cmd.getDataFim()));
			ps.setLong(5, matriculaDocente);
			ps.setLong(6, idStatus);
			
			return ps;
		}, keyHolder);

		return (long) keyHolder.getKey();
	}
	
	@Transactional
	public long cadastrarPedidoProtocolo(CadastroProtocoloCmd cmd, long idProtocolo) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = env.getProperty("com.linecode.protocolo.dao.ProtocoloDao.cadastrarPedidoProtocolo");

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, cmd.getQuantidade());
			ps.setLong(2, cmd.getEspecie());
			ps.setLong(3, cmd.getBioterio());
			ps.setLong(4, idProtocolo);
			
			return ps;
		}, keyHolder);

		return (long) keyHolder.getKey();
	}
}
