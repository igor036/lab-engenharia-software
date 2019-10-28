#language:pt

@getListaSugestaoDocente
Funcionalidade: consultar uma lista de sugestão de docente por nome.
	
	Cenário: nome vazio.
	Quando consultar a lista de sugestão informando um nome vazio com os seguintes dados
	| nome | MsgEsperada 						   |
	|      | Informe o nome da consulta.  		   |
	
	Então devo recer uma excecao solicitando para informar o nome.