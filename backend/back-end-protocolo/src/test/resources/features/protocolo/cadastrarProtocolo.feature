#language:pt
	
@Cadastro
Funcionalidade: Efetuar o cadastro de um determinado protocolo no sistema
	
	Cenário: Dados Invalidos
	Quando desejar efetuar um cadastro com pedidos invalidos, sendo que os dados do cadastro estao validos, informando os seguintes dados
	| justificativa 			| resumoPt   | resumoEn | dataInicio | dataFim    | idEspecie | quantidade |  idBioterio |  msgEsperada 			       |
	| abc						|  abc		 | abc 		| 22/01/2019 | 25/01/2019 | 0		  |	1		   | 	1		 |  Id espécie inválido.     	   |
	| abc						|  abc		 | abc 		| 22/01/2019 | 25/01/2019 | 1		  |	0		   | 	1		 |  Quantidade espécie inválida.   |
	| abc						|  abc		 | abc 		| 22/01/2019 | 25/01/2019 | 1		  |	1		   | 	0		 |  Id biotério inválido.     	   |
	
	Então devo receber uma Excecao Negocio exception$.
	
	Quando desejar efetuar um cadastro de um determinado protocolo informando um cadastro invalido com pedidos validos, informando os seguintes dados
	| justificativa 			| resumoPt   | resumoEn | dataInicio | dataFim    | msgEsperada 			       |
	|  							|  abc		 | abc 		| 22/01/2019 | 25/01/2019 | Informe a justificativa. 	   |
	|  abc						|    		 | abc 		| 22/01/2019 | 25/01/2019 | Informe o resumo em português. |
	|  abc						|  abc		 |  		| 22/01/2019 | 25/01/2019 | Informe o resumo em inglês.    |
	|  abc						|  abc		 |  abc		|			 | 25/01/2019 | Informe a data de inicio.	   |
	|  abc						|  abc		 |  abc		| 22/01/2019 | 		 	  | Informe a data fim.			   |
	|  abc						|  abc		 |  abc		| 25/01/2019 | 22/01/2019 | Período de tempo inválido. A data fim deve ser maior que a data início.|
	
	Então devo receber uma Excecao Negocio exception$. 