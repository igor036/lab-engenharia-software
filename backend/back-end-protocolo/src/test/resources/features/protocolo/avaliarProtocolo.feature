#language:pt
	
@AvaliarProtocolo
Funcionalidade: Efetuar a avaliacao de um determinado protocolo que foi encaminhado.
	Cenário: Dados Invalidos
	Quando o doscente desejar avaliar um protocolo informando uma avaliacao invalida com os seguintes dados
	| deferido | descricao | idProtocolo | msgEsperada 				|
	| boolean  | 		   | 1			 | Informe a observação.	|
	| boolean  | abc	   | 0			 | ID do protocolo inválido.|
	
	Então devo receber uma Excecao informando que os dados da avliacao estao invalidos exception$.