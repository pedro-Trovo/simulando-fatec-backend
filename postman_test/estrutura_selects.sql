
drop database dbvestibular;


/*
							   REFERENTE A ALUNO                     |                     REFERENTE A VESTIBULAR
						  Aluno [origem]		-> Conquista Obtida	-> Conquista <-		Vestibular [origem]
	Aluno [origem]	-> Prova Efetuada		-> Questao Resolvida	-> Questao <-	Prova (imagens e alternativas) <-	Vestibular [origem]


	O "Aluno" e "Vestibular" são as ORIGENS DE TUDO, ou seja, não é necessário criar NADA antes deles.
		- O "Aluno" possui um array de "Conquista Obtida" e "Prova Efetuada".
		- O "Vestibular" possui um array de "Conquista" e "Prova".
			A "Conquista Obtida" necessita de uma "Conquista", que por sua vez necessita de um "Vestibular" [origem] previamente criado.
			A "Prova Efetuada" pode conter uma "Questão Obtida", que por sua vez necessita de uma "Questão", e esta necessita de uma "Prova", e por fim necessida de um "Vestibular" [origem] previamente criado.


    Se um "Aluno" for excluido do Banco de Dados, a "Conquista Obtida" (conquistas obtidas por ele) e "Prova Efetuada" (provas efetuadas por ele, o que inclui as Questões Resolvidas) ligada a ele serão excluidas.

    Se um "Vestibular" for exlcuido do Banco de Dados, a "Conquista" (conquistas referentes a um vestibular) ligada a ele será excluida.
    O mesmo vale para a "Prova", qualquer prova ligada ao "Vestibular" excluido também será excluido, o que inclui excluir a "Questao" (questões da prova) ligada a ela, bem como suas Imagens e Alternativas.
    
    Note que:
		- Excluir a tabela de "Conquista" com Conquistas Obtidas existentes não será possível. É necessário excluir primeiro as Conquistas Obtidas referentes àquela Conquista a ser excluida.
        - Excluir a tabela de "Vestibular" com Provas Efetuadas existentes não será possível. É necessário excluir primeiro as Provas Efetuadas referentes àquele (Prova que faz referencia ao) Vestibular a ser excluida.
        - Entre outros cenários semelhantes.
*/

use dbvestibular;


/* Tabelas voltadas para o Aluno */
select * from aluno;
select * from conquista_obtida;
select * from prova_efetuada;
select * from questao_resolvida;



/* Tabelas voltadas para o Vestibular */
select * from vestibular;
select * from conquista;
select * from prova;

	/* Tabelas voltadas para a Prova */
select * from questao;
select * from questao_imgs;
select * from alternativa;