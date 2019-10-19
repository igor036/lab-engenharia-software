/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 31/08/2019
 */
package com.linecode.protocolo.servico;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linecode.compartilhado.excecao.ExcecaoAplicacao;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.servico.DocenteServico;
import com.linecode.protocolo.cmd.CadastroProtocoloCmd;
import com.linecode.protocolo.dao.ProtocoloDao;
import com.linecode.util.servico.UtilServico;

import io.jsonwebtoken.lang.Assert;

@Service
public class ProtocoloServico {

	@Autowired
	private ProtocoloDao protocoloDao;

	@Autowired
	private DocenteServico docenteServico;

	@Autowired
	private UtilServico utilServico;

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * Efetua o cadastro de um protocolo e o vincula ao docente logado
	 * 
	 * A data fim deve ser obrigatóriamente maior que a data de início.
	 * 
	 * @param cmd - dados do protocolo {@link CadastroProtocoloCmd}
	 */
	@Transactional
	public void cadastrarProtocolo(CadastroProtocoloCmd cmd) {

		Assert.notNull(cmd, "Informe os dados do protocolo");

		Set<ConstraintViolation<CadastroProtocoloCmd>> violacoes = validator.validate(cmd);

		if (violacoes.isEmpty()) {

			if (cmd.getDataFim() != null && cmd.getDataInicio().compareTo(cmd.getDataFim()) >= 0) {
				throw new ExcecaoNegocio("Período de tempo inválido. A data fim deve ser maior que a data início.");
			}

			long idStatusInicial = utilServico.getIdStatusInicialProtocolo();
			long idProtocolo = protocoloDao.cadastrarProtocolo(cmd,
					docenteServico.getDadosDocenteLogado().getMatricula(), idStatusInicial);

			if (idProtocolo > 0) {
				cmd.getLitaPedidoProtocolo().forEach(pedido -> {
					pedido.setIdProtocolo(idProtocolo);
					protocoloDao.cadastrarPedidoProtocolo(pedido);
				});
			} else {
				throw new ExcecaoAplicacao("Houve um erro ao cadastrar o protocolo.", null);
			}

		} else {
			throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
		}
	}
}
