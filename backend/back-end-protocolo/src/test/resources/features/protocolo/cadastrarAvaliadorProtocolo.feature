#language:pt
	
@CadastroAvaliadorProtocolo
Funcionalidade: Efetuar o cadastro de um determinado avaliador para um determinado protocolo
	Cenário: Dados Invalidos
	Quando desejar efetuar o cadastro de um determinado avaliador para um protocolo informando um cadastro invalido com os seguintes dados
	| idAvaliador | idProtocolo |  msgEsperada 			       |
	| 0			  | 1			| ID do avaliador inválido.    |
	| 1			  | 0			| ID do protocolo inválido.    |
	
	Então devo receber uma Excecao informando que o id do avaliador ou o id do protocolo estão inválidos exception$.