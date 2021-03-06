/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.docente.servico;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.linecode.compartilhado.dto.OpcaoDto;
import com.linecode.compartilhado.excecao.ExcecaoAplicacao;
import com.linecode.compartilhado.excecao.ExcecaoNegocio;
import com.linecode.docente.cmd.CadastroDocenteCmd;
import com.linecode.docente.dao.DocenteDao;
import com.linecode.docente.dto.DocenteDto;


@Service
public class DocenteServico {

    @Autowired
    private DocenteDao docenteDao;
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;
    
    @Autowired
    private Validator validator;

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
    
    /**
     * Efetua o cadastro de um docente, gerando sua senha com o padrão
     * primeiro_nome.ultimo_nome+sistema.complemento.senha.padrao
     * em seguida envia a senha para o e-mail do docente.
     * 
     * @param dados do docente {@link CadastroDocenteCmd}
     */
    @PreAuthorize("@autorizacaoServico.isAutorizacaoAdmin()")
    @Transactional
    public void cadastrarDocente(CadastroDocenteCmd cmd) {
    	
        Assert.notNull(cmd, "Informe os dados do docente");

        Set<ConstraintViolation<CadastroDocenteCmd>> violacoes = validator.validate(cmd);

        if (violacoes.isEmpty()) {

            cmd.setSenha(gerarSenha(cmd.getNome()));

            if (docenteDao.cadastrarDocente(cmd)) {
                
                String corpoEmail = gerarCorpoEmailSenha(cmd.getNome(), cmd.getSenha());
                String tituloEmail = env.getProperty("sistema.titulo.email.senha");
                
                enviarEmail(cmd.getEmail(), tituloEmail, corpoEmail);
                
            } else {
            	throw new ExcecaoAplicacao("Erro ao cadastrar docente", null);
            }
            
        } else {
        	throw new ExcecaoNegocio(violacoes.stream().findFirst().get().getMessage());
        }
    }
    
    /**
     * Retorna uma lista de sugestão de docentes por nome de consulta.
     * O metodo ira retornar uma lista com cinco docentes que contenham
     * o <b>nome</b> informado.
     * 
     * @param nome - o nome do docente.
     * @return lista de opção {@link List<OpcaoDto<Long>>}
     */
    @PreAuthorize("@autorizacaoServico.isAutenticado()")
    public List<OpcaoDto<Long>> getListaSugestaoDocente(String nome) {
    	
    	if (StringUtils.isEmpty(nome) || StringUtils.isEmpty(nome.trim())) {
    		throw new ExcecaoNegocio("Informe o nome da consulta.");
    	}
    	
    	return docenteDao.getListaSugestaoDocente(nome);
    }

    /**
     * Gera a senha padrão de um determinado docente
     * apartir do nome no formato:
     * primeiro_nome.ultimo_nome+sistema.complemento.senha.padrao
     * 
     * @param nome do docente {@link String}
     * @return senha padrão {@link String}
     * 
     */
    private String gerarSenha(String nome) {

        String[] nomeSeparado = nome.split(" ");
        String primeiroNome = nomeSeparado[0];
        String ultimoNome = nomeSeparado[nomeSeparado.length - 1];
        StringBuilder senha = new StringBuilder();

        senha.append(primeiroNome).append(".").append(ultimoNome)
                .append(env.getProperty("sistema.complemento.senha.padrao"));

        return senha.toString();
    }
    
    /**
     * Gera o corpo do email que informa
     * a senha do docente.
     * 
     *  @param nomeDocente  {@link String}
     *  @param senhaDocente {@link String}
     *  
     *  @return corpo do email {@link String}
     */
    private String gerarCorpoEmailSenha(String nomeDocente, String senhaDocente) {
        
        StringBuilder corpoEmail = new StringBuilder();
        
        corpoEmail
            .append("Caro(a) ")
            .append(nomeDocente)
            .append(" Informamos que a sua senha para o portal de protocolo é: ")
            .append(senhaDocente)
            .append(" Por-favor, não compartilhe a senha com os demais docentes.")
            .append(" Atenção: E-mail informativo, não responda este email.");
        
        return corpoEmail.toString();
    }
    
    /**
     * Envia um email para um determinado destino
     * 
     *  @param emailDestino - email de destino {@link String}
     *  @param titulo - titulo do email {@link String}
     *  @param corpo - conteudo do email {@link String}
     */
    private void enviarEmail(String emailDestino, String titulo, String corpo) {
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestino);
        email.setSubject(titulo);
        email.setText(corpo);
        
        javaMailSender.send(email);
        
    }
}
