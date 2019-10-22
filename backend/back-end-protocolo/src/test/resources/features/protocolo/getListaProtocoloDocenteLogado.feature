#language:pt

@GetListaProtocoloDocenteLogado
Funcionalidade: Efetuar uma consulta dos cadastros do docente logado com algumas opções de filtro

	Cenário: Dados Invalidos
	Quando desejar efetuar uma consultla dos meus protocolos informando um filtro invalido com os seguintes dados
	| tipo 			| status   | idProtocolo | msgEsperada 					  |
	| C				| ABERTO   | 0 			 | Código do protocolo inválido.  |
	| S				|		   | 1 			 | Informe o status.    		  |
	|    			| ABERTO   | 1			 | Informe o tipo de consulta.    |
	
	Então devo receber uma Excecao Negocio.