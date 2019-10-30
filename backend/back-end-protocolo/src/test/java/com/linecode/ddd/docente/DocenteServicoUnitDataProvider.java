/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 30/10/2019
 */
package com.linecode.ddd.docente;

import org.testng.annotations.DataProvider;

import com.linecode.docente.cmd.CadastroDocenteCmd;

public class DocenteServicoUnitDataProvider {
	
	private static final String MSG_EMAIL_INVALIDO = "Informe o e-mail./E-mail inválido.";
	private static final String MSG_INFORMAR_NOME = "Informe o nome do docente";
	private static final String MSG_INFORMAR_EMAIL_LOGIN = "Informe o email para efetuar o login";
	private static final String MSG_INFORMAR_SENHA_LOGIN = "Informe a senha para efetuar o login";
	
	private static final String STRING_ESPACO_EM_BRANCO = "   ";
	private static final String STRING_VAZIA = "";
	private static final String EMAIL = "mofu@gmail.com";
	private static final String SENHA = "mofusenha";
	private static final String NOME = "Igor Mofu";
	private static  final long ID_PERFIL = 1L;
	
	@DataProvider(name = "testGetDocentePorEmailSenhaDataProvider")
	public static Object[][] testGetDocentePorEmailSenhaDataProvider() {
		return new Object[][] {
			{null, SENHA, MSG_INFORMAR_EMAIL_LOGIN},
			{STRING_VAZIA, SENHA, MSG_INFORMAR_EMAIL_LOGIN},
			{STRING_ESPACO_EM_BRANCO, SENHA, MSG_INFORMAR_EMAIL_LOGIN},
			{EMAIL, null, MSG_INFORMAR_SENHA_LOGIN},
			{EMAIL, STRING_VAZIA, MSG_INFORMAR_SENHA_LOGIN},
			{EMAIL, STRING_ESPACO_EM_BRANCO, MSG_INFORMAR_SENHA_LOGIN},
		};
	}
	
	@DataProvider(name = "testCadastrarDocenteDataProvider")
	public static Object[][] testCadastrarDocenteDataProvider() {
		
		CadastroDocenteCmd emailNulo = new CadastroDocenteCmd(null, NOME, ID_PERFIL);
		CadastroDocenteCmd emailVazio = new CadastroDocenteCmd(STRING_VAZIA, NOME, ID_PERFIL);
		CadastroDocenteCmd emailEspacoEmBranco = new CadastroDocenteCmd(STRING_ESPACO_EM_BRANCO, NOME, ID_PERFIL);
		CadastroDocenteCmd nomeNulo = new CadastroDocenteCmd(EMAIL, null, ID_PERFIL);
		CadastroDocenteCmd nomeVazio = new CadastroDocenteCmd(EMAIL, STRING_VAZIA, ID_PERFIL);
		CadastroDocenteCmd nomeEspacoEmBranco = new CadastroDocenteCmd(EMAIL, STRING_VAZIA, ID_PERFIL);
		
		return new Object[][] {
			{null, "Informe os dados do docente"},
			{emailNulo, MSG_EMAIL_INVALIDO},
			{emailVazio, MSG_EMAIL_INVALIDO},
			{emailEspacoEmBranco, MSG_EMAIL_INVALIDO},
			{nomeNulo, MSG_INFORMAR_NOME},
			{nomeVazio, MSG_INFORMAR_NOME},
			{nomeEspacoEmBranco, MSG_INFORMAR_NOME},
		};
	}
	
	@DataProvider(name = "testGetListaSugestaoDocenteDataProvider")
	public static Object[][] testGetListaSugestaoDocenteDataProvider() {
		return new Object[][] {{null}, {STRING_VAZIA}, {STRING_ESPACO_EM_BRANCO}};
	}
}
