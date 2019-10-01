#language:pt

@login
Funcionalidade: resgatar os dados de login no sistema através do email e da senha.
	Como usuario desejo informar recuperar os dados de login do docente.
	
	Cenário: EmailSenhaVazio
	Quando eu desejar logar no sistema informando os seguintes dados
	| Email 			  | Senha | MsgEsperada 						 |
	|       			  | abc   |Informe o email para efetuar o login  |
	| fulano@gmail.com    |       |Informe a senha para efetuar o login  |
	
	
	Então devo recer um Illegal Argument exception$.