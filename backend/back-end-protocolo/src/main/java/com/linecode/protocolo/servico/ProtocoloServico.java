/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.servico;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linecode.compartilhado.excecao.ExcecaoAplicacao;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.servico.DocenteServico;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.dao.ProtocoloDao;

import io.jsonwebtoken.lang.Assert;

@Service
public class ProtocoloServico {
	
	@Autowired
	private ProtocoloDao protocoloDao;
	
	@Autowired
	private DocenteServico docenteServico;
	
	@Autowired
    private Validator validator;
	
	/**
	 * Efetua o cadastro de um protocolo e o vincula
	 * ao docente logado
	 * 
	 * A data fim deve ser obrigatóriamente maior que a data
	 * de início.
	 * 
	 * @param cmd - dados do protocolo {@link CadastroProtocoloCmd}
	 */
	public void cadastrarProtocolo(CadastroProtocoloCmd cmd) {
		
		Assert.notNull(cmd, "Informe os dados do protocolo");

        Set<ConstraintViolation<CadastroProtocoloCmd>> violacoes = validator.validate(cmd);
        
        if (violacoes.isEmpty()) {
        	
        	if (cmd.getDataInicio().compareTo(cmd.getDataFim()) >= 0) {
        		throw new ExcecaoNegocio("Período de tempo inválido. A data fim deve ser maior que a data início.");
        	}
        	
        	if (!protocoloDao.cadastrarProtocolo(cmd, docenteServico.getDadosDocenteLogado().getMatricula())) {
        		throw new ExcecaoAplicacao("Houve um erro ao cadastrar o protocolo.", null);
        	}
        	
        } else {
        	throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
        }
	}
}