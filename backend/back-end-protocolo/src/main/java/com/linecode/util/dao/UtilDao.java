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

import com.linecode.util.dto.OpcaoDto;

@Repository
@PropertySource("com/linecode/util/dao/UtilDao.xml")
public class UtilDao {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OpcaoDto<Long>> getListaPerfil() {
        return jdbcTemplate.query(env.getProperty("com.linecode.util.dao.UtilDao.getListaPerfil"),
                UtilMapeadorLinha.getOpcaoDtoMapeadorLinha());
    }
}
