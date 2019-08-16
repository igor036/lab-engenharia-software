/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.example.aplicacao.configuracao.seguranca.servico;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenJwtAutenticacaoServico {
	
	// EXPIRATION_TIME = 10 dias
	private static final long TOKEN_JWT_TEMPO_EXPIRACAO = 860_000_000;
	private  static final String TOKEN_JWT_SECRET = "MySecret";
	private  static final String TOKEN_JWT_PREFIXO = "Bearer";
	private  static final String TOKEN_JWT_CABECALHO = "Authorization";

	/**
	 * Metodo responsavel por adicionar o token JWT
	 * no cabecalho 'Authorization' da resposta
	 * 
	 * O token é formado pelo nome do usuario e a data de expiração.
	 * 
	 * @param {@link HttpServletResponse} objeto de resposta.
	 * @param {@link String} username login do usuario.
	 */
	public static void addAuthentication(HttpServletResponse response, String username) {
		
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(getDataExpiracao())
				.signWith(SignatureAlgorithm.HS512, TOKEN_JWT_SECRET).compact();

		response.addHeader(TOKEN_JWT_CABECALHO, TOKEN_JWT_PREFIXO + " " + JWT);
	}

	/**
	 * Metodo responsavel por recuperar os dados
	 * do usuario autenticado.
	 * 
	 * {@link HttpServletRequest} objeto de resposta.
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(TOKEN_JWT_CABECALHO);

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(TOKEN_JWT_SECRET)
					.parseClaimsJws(token.replace(TOKEN_JWT_PREFIXO, ""))
					.getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}
	
	private static Date getDataExpiracao() {
		return new Date(System.currentTimeMillis() + TOKEN_JWT_TEMPO_EXPIRACAO);
	}
}
