#language:pt
	
@Cadastro
Funcionalidade: Efetuar o cadastro de um  docente no sistema.
	Como coordenador ou usuário administrador, desejo efetuar o cadastro
	de um determinado docente.
	
	Cenário: Dados Invalidos
	Quando desejar efetuar um cadastro informado um cadastro invalido com os seguintes dados
	| email 			| nome   | idPerfil | MsgEsperada 						|
	| 	    			| fulano | 1        |Informe o e-mail./E-mail inválido.	|
	| fulano 			| fulano | 1        |Informe o e-mail./E-mail inválido.	|
	| fulano@gmail.com 	|        | 1        |Informe o nome do docente			|
	| fulano@gmail.com 	| fulano | 0        |Id do perfil inválido.				|
	
	Então devo receber uma Excecao Negocio exception$.