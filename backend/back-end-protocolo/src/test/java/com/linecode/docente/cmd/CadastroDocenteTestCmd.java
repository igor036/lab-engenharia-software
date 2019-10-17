package com.linecode.docente.cmd;

public class CadastroDocenteTestCmd extends CadastroDocenteCmd {
	
	private String msgEsperada;
	
	public CadastroDocenteTestCmd(String email,String nome, long idPerfil, String msgEsperada) {
		super(email, nome, idPerfil);
		this.msgEsperada = msgEsperada;
	}
	
	public String getMsgEsperada() {
		return msgEsperada;
	}
}
