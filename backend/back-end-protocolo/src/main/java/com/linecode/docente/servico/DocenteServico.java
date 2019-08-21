/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.servico;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.linecode.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.dao.DocenteDao;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.publico.excecao.ExcecaoAplicacao;
import com.linecode.publico.excecao.ExcecaoNegocio;

import io.jsonwebtoken.lang.Assert;

@Service
public class DocenteServico {

    @Autowired
    private DocenteDao docenteDao;

    @Autowired
    private Validator validator;

    @Autowired
    private Environment env;

    /**
     * Recupera os dados do docente apartir do email e da senha
     * 
     * @param {@link String} email
     * @param {@link String} senha
     */
    public DocenteDto getDocentePorEmailSenha(String email, String senha) {

        Assert.hasText(email, "Informe o email para efetuar o login");
        Assert.hasText(senha, "Informe a senha para efetuar o login");

        return docenteDao.getDocentePorEmailSenha(email, senha);
    }

    /**
     * Retorna os dados do docente logado {@link DocenteDto}
     * 
     * Enviar email com a senha { (primeironome).(ultimonome)@123 }
     * 
     */
    @PreAuthorize("@autorizacaoServico.isAutenticado()")
    public DocenteDto getDadosDocenteLogado() {
        return (DocenteDto) SecurityContextHolder.getContext().getAuthentication();
    }
    
    @PreAuthorize("@autorizacaoServico.isAutorizacaoAdmin()")
    public String cadastrarDocente(CadastroDocenteCmd cmd) {

        Assert.notNull(cmd, "Informe os dados do docente");

        Set<ConstraintViolation<CadastroDocenteCmd>> violacoes = validator.validate(cmd);

        if (violacoes.isEmpty()) {

            cmd.setSenha(gerarSenha(cmd.getNome()));

            if (docenteDao.cadastrarDocente(cmd)) {
                return TokenJwtAutenticacaoServico
                        .gerarTokenDocente(getDocentePorEmailSenha(cmd.getEmail(), cmd.getSenha()));
            }

            throw new ExcecaoAplicacao("Erro ao cadastrar docente", null);
        }

        throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
    }

    private String gerarSenha(String nome) {

        String[] nomeSeparado = nome.split(" ");
        String primeiroNome = nomeSeparado[0];
        String ultimoNome = nomeSeparado[nomeSeparado.length - 1];
        StringBuilder senha = new StringBuilder();

        senha.append(primeiroNome).append(".").append(ultimoNome)
                .append(env.getProperty("sistema.complemento.senha.padrao"));

        return senha.toString();
    }
}
