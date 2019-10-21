/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.dao.mapeadorlinha.OpcaoDtoMapeadorLinha;
import com.linecode.util.dto.OpcaoDto;

@Repository
@PropertySource("com/linecode/util/dao/UtilDao.xml")
public class UtilDao {

    private static final OpcaoDtoMapeadorLinha OPCAO_DTO_MAPEADOR_LINHA = new OpcaoDtoMapeadorLinha();

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<OpcaoDto<Long>> getListaPerfil() {
        return jdbcTemplate.query(env.getProperty("com.linecode.util.dao.UtilDao.getListaPerfil"),
                OPCAO_DTO_MAPEADOR_LINHA);
    }

    @Transactional(readOnly = true)
    public List<OpcaoDto<Long>> getListaEspecie() {
        return jdbcTemplate.query(env.getProperty("com.linecode.util.dao.UtilDao.getListaEspecie"),
                OPCAO_DTO_MAPEADOR_LINHA);
    }

    @Transactional(readOnly = true)
    public List<OpcaoDto<Long>> getListaBioterio() {
        return jdbcTemplate.query(env.getProperty("com.linecode.util.dao.UtilDao.getListaBioterio"),
                OPCAO_DTO_MAPEADOR_LINHA);
    }

    @Transactional(readOnly = true)
    public long getIdStatusPorDescricao(String descricaoStatus) {
        return jdbcTemplate.queryForObject(env.getProperty("com.linecode.util.dao.UtilDao.getIdStatusPorDescricao"),
                Long.class, descricaoStatus);
    }

    @Transactional(readOnly = true)
    public List<OpcaoDto<Long>> getListaStatusProtocolo() {
        return jdbcTemplate.query(env.getProperty("com.linecode.util.dao.UtilDao.getListaStatusProtocolo"),
                OPCAO_DTO_MAPEADOR_LINHA);
    }
}
