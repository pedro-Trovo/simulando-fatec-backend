drop database dbvestibular;


/*
	Aluno [origem] 	-> Conquista Obtida		-> Conquista <- 	Vestibular [origem]
					-> Questao Resolvida	-> Questao <-	Prova (imagens e alternativas) <-	Vestibular [origem]


	O "Aluno" e "Vestibular" são as ORIGENS DE TUDO, ou seja, não é necessário criar NADA antes deles.
		- O "Aluno" possui um array de "Conquista Obtida" e "Questao Resolvida".
		- O "Vestibular" possui um array de "Conquista" e "Prova".
			A "Conquista Obtida" necessita de uma "Conquista", que por sua vez necessita de um "Vestibular" [origem] previamente criado.
			A "Questão Resolvida" necessita de uma "Questão", que por sua vez necessita de uma "Prova", e esta necessita de um "Vestibular" [origem] previamente criado.


    Se um "Aluno" for excluido do Banco de Dados, a "Conquista Obtida" (conquistas obtidas por ele) e "Questao Resolvida" (questões resolvidas por ele) ligada a ele serão excluidas.

    Se um "Vestibular" for exlcuido do Banco de Dados, a "Conquista" (conquistas referentes a um vestibular) ligada a ele será exlcuida.
    O mesmo vale para a "Prova", qualquer prova ligada ao "Vestibular" excluido também será excluido, o que inclui excluir a "Questao" (questões da prova) ligada a ela, bem como suas imagens e alternativas.
*/

use dbvestibular;


/* Tabelas voltadas para o Aluno */
select * from aluno;
select * from conquista_obtida;
select * from questao_resolvida;



/* Tabelas voltadas para o Vestibular */
select * from vestibular;
select * from conquista;
select * from prova;

/* Tabelas voltadas para a Prova */
select * from questao;
select * from questao_imgs;
select * from alternativa;