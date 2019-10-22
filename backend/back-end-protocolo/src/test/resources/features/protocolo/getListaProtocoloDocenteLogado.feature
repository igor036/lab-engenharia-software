#language:pt

@GetListaProtocoloDocenteLogado
Funcionalidade: Efetuar uma consulta dos cadastros do docente logado com algumas opções de filtro

	Cenário: Dados Invalidos
	Quando desejar efetuar uma consultla dos meus protocolos informando um filtro invalido com os seguintes dados
	| tipo 			| idStatus | idProtocolo | msgEsperada 					  |
	| C				| 1		   | 0 			 | Código do protocolo inválido.  |
	| S				| 0		   | 1 			 | Código do status inválido.	  |
	|    			| 1		   | 1			 | Informe o tipo de consulta.    |
	
	Então devo receber uma Excecao Negocio.