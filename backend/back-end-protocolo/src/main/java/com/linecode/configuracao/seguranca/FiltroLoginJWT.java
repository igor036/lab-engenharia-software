/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.linecode.configuracao.seguranca.servico.TokenJwtAutenticacaoServico;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.docente.servico.DocenteServico;

public class FiltroLoginJWT extends AbstractAuthenticationProcessingFilter {

    private DocenteServico docenteServico;

    protected FiltroLoginJWT(String url, ApplicationContext contexto) {
        super(new AntPathRequestMatcher(url));
        this.docenteServico = contexto.getBean(DocenteServico.class);
    }

    /**
     * metodo responsavel por receber as requisições e efetuar a autenticacao pelo token.
     * 
     * @throws IOException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        adicionarCrossNaResposta(response);

        BufferedReader reader = request.getReader();
        JacksonJsonParser parser = new JacksonJsonParser();
        String json = reader.lines().collect(Collectors.joining());
        Map<String, Object> mapaCredenciais = parser.parseMap(json);

        // @formatter:off
        UsernamePasswordAuthenticationToken tokenAutenticacao = new UsernamePasswordAuthenticationToken(
            mapaCredenciais.get("email"),
            mapaCredenciais.get("senha"),
            Collections.emptyList()
         );
        // @formatter:on
              
        Authentication autenticacao = getAuthenticationManager().authenticate(tokenAutenticacao);
        
        if (autenticacao == null) {
            handlerErroLogin(response);
        }
		
		return autenticacao;
	}
	
	/**
	 * Metodo que é chamado quando a autenticação é realizada
	 * ele chama o metodo que ira adicionat o token JWT no 
	 * cabeçalho 'Authorization'
	 * */
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication autenticacao) throws IOException, ServletException {
		
		TokenJwtAutenticacaoServico.addAuthentication(response,(DocenteDto)autenticacao);
	}
	
	/**
	 * Metodo responsavel por gerar o  cabecalho
	 * de resposta para acesso negado.
	 * 
	 *  Utilizado quando o usuario não é autenticado.
	 *  
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"Authentication Failed");
	}
	
	/**
	 * Metodo que retornar o manager de login
	 */
	@Override
    public AuthenticationManager getAuthenticationManager() {
        return (Authentication authentication) -> {

            String email = authentication.getName();
            String senha = authentication.getCredentials().toString();

            return docenteServico.getDocentePorEmailSenha(email, senha);

        };
    }
	
	/**
	 * Adiciona permissão de cross
	 * no cabeçalho de resposta.
	 * 
	 * @param resposta {@link HttpServletResponse}
	 */
	private void adicionarCrossNaResposta(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
	}
	
	/**
	 * Faz o tratamento de erro de login na resposta
	 * 
	 * @param response {@link HttpServletResponse}
	 * @throws IOException 
	 */
	private void handlerErroLogin(HttpServletResponse response) throws IOException {
	    response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Usuário/Senha inválido(a)");
	}
}
