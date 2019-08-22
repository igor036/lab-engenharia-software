/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca.servico;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecode.docente.dto.DocenteDto;
import com.linecode.compartilhado.excecao.ExcecaoAplicacao;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenJwtAutenticacaoServico {
	
	// EXPIRATION_TIME = 10 dias
	private static final long TOKEN_JWT_TEMPO_EXPIRACAO = 860_000_000;
	private static final String TOKEN_JWT_SECRET = "LineCode$ecret";
	private static final String TOKEN_JWT_PREFIXO = "LineCode";
	private static final String TOKEN_JWT_CABECALHO = "Authorization";
	private static ObjectMapper mapeadorJson = new ObjectMapper();
	
	private TokenJwtAutenticacaoServico() {
	    //somente metodos estaticos
	}
	
	
	/**
	 *  Metodo responsável por retornar
	 *  o token jwt do usuario
	 *  
	 *  @param docente {@link DocenteDto}
	 *  @return tokenJWT {@link String}
	 */
	public static String gerarTokenDocente(DocenteDto docente) {
	    
	    try {
	        
	        String jsonDocente = mapeadorJson.writeValueAsString(docente);
	        return TOKEN_JWT_PREFIXO + " " + Jwts.builder().setSubject(jsonDocente)
	                .setExpiration(getDataExpiracao())
	                .signWith(SignatureAlgorithm.HS512, TOKEN_JWT_SECRET).compact();
	        
	    }catch (JsonProcessingException e) {
            throw new ExcecaoAplicacao("Erro ao gerar token do docente", e);
        }
	}
	
	/**
	 * Metodo responsavel por adicionar o token JWT
	 * no cabecalho 'Authorization' da resposta
	 * 
	 * O token é formado pelo o bjeto do docente {@link DocenteDto} e a data de expiração.
	 * 
	 * @param {@link HttpServletResponse} objeto de resposta.
	 * @param {@link String} username login do usuario.
	 * @throws IOException 
	 */
	public static void addAuthentication(HttpServletResponse response, DocenteDto docente) throws IOException {
		
	    String token = gerarTokenDocente(docente);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.addHeader(TOKEN_JWT_CABECALHO, token);
		response.getWriter().print(token);
		
	}

	/**
	 * Metodo responsavel por recuperar os dados
	 * do usuario autenticado.
	 * 
	 * {@link HttpServletRequest} objeto de resposta.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception 
	 */
	public static DocenteDto getAuthentication(HttpServletRequest request) throws IOException  {
		
		String token = request.getHeader(TOKEN_JWT_CABECALHO);

		if (StringUtils.isEmpty(token)) {
		    return null;
		}
		
		String jsonDocente = Jwts.parser()
                .setSigningKey(TOKEN_JWT_SECRET)
                .parseClaimsJws(token.replace(TOKEN_JWT_PREFIXO, ""))
                .getBody()
                .getSubject();
		
		mapeadorJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return mapeadorJson.readValue(jsonDocente, DocenteDto.class);
	}
	
	private static Date getDataExpiracao() {
		return new Date(System.currentTimeMillis() + TOKEN_JWT_TEMPO_EXPIRACAO);
	}
}
