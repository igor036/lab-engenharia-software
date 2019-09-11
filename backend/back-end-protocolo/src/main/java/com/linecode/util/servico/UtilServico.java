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
     *  @return lista de perfis {@link List<Long>}
     */
    @PreAuthorize("@autorizacaoServico.isAutorizado('ADMIN')")
    public List<OpcaoDto<Long>> getListaPerfil() {
        return utilDao.getListaPerfil();
    }
    
    /**
     *
     * Retorna a lista de especies
     * cadastradas no sistema.
     * 
     * @return lista de especies {@link List<Long>}
     */
    @PreAuthorize("@autorizacaoServico.isAutorizado('ADMIN')")
    public List<OpcaoDto<Long>> getListaEspecie() {
    	return utilDao.getListaEspecie();
    }
    
    /**
    *
    * Retorna a lista de bioterio
    * cadastradas no sistema.
    * 
    * @return lista de bioterio {@link List<Long>}
    */
    @PreAuthorize("@autorizacaoServico.isAutorizado('ADMIN')")
    public List<OpcaoDto<Long>> getListaBioterio() {
    	return utilDao.getListaBioterio();
    }
}
