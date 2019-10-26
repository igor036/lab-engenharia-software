/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.dao.mapeadorlinha.OpcaoDtoMapeadorLinha;
import com.linecode.compartilhado.dto.OpcaoDto;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.dto.DocenteDto;

@Repository
@PropertySource("com/linecode/docente/dao/DocenteDao.xml")
public class DocenteDao {

	private static final OpcaoDtoMapeadorLinha OPCAO_DTO_MAPEADOR_LINHA = new OpcaoDtoMapeadorLinha();
	
    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTamplate;

    @Transactional(readOnly = true)
    public DocenteDto getDocentePorEmailSenha(String email, String senha) {
        return jdbcTamplate.query(env.getProperty("com.linecode.docente.dao.DocenteDao.getDocentePorEmailSenha"),
                DocenteMapeadorLinha.getDocenteDtoMapeadorLinha(), email, senha);
    }

    @Transactional
    public boolean cadastrarDocente(CadastroDocenteCmd cmd) {
        return jdbcTamplate.update(env.getProperty("com.linecode.docente.dao.DocenteDao.cadastrarDocente"),
                cmd.getNome(), cmd.getEmail(), cmd.getSenha(), cmd.getIdPerfil()) == 1;
    }
    
    @Transactional(readOnly = true)
    public List<OpcaoDto<Long>> getListaSugestaoDocente(String nome) {
    	return jdbcTamplate.query(env.getProperty("getListaSugestaoDocente"), OPCAO_DTO_MAPEADOR_LINHA, nome);
    }
}
