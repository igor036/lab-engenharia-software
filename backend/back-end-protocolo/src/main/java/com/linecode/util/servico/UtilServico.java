/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.util.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.linecode.util.dao.UtilDao;
import com.linecode.util.dto.OpcaoDto;

@Service
public class UtilServico {

    @Autowired
    private UtilDao utilDao;
    
    /**
     * Retorna a lista de perfis
     * cadastrados no sistema.
     * 
     *  @param lista de perfis {@link List<PerfilDto>}
     */
    @PreAuthorize("@autorizacaoServico.isAutenticado()")
    public List<OpcaoDto<Long>> getListaPerfil() {
        return utilDao.getListaPerfil();
    }
    
}
