INSERT INTO equivalencia_disciplina.faculdade (nome) VALUES
	 ('Universidade Federal de Uberlândia'),
	 ('Universidade Federal de Minas Gerais'),
	 ('Universidade de São Paulo');

INSERT INTO equivalencia_disciplina.curso (nome,faculdade_id) VALUES
	 ('Matemática',2),
	 ('Sistemas de Informação',1),
	 ('Ciência da Computação',1);

INSERT INTO equivalencia_disciplina.disciplina (nome,codigo_origem,ementa,programa,carga_horaria,faculdade_id,curso_id) VALUES
	 ('Cálculo I','FAMAT39214','Números reais e funções; Noções de limites e continuidade; Derivadas e suas aplicações; A integral indefinida, a integral definida e suas aplicações','Números reais e funções; Noções de limites e continuidade; Derivadas e suas aplicações; A integral indefinida, a integral definida e suas aplicações','90',1,2),
	 ('ESTUDOS LÓGICOS MATEMÁTICOS Il','90451','A disciplina aborda o estudo das integrais múltiplas, integrais de linha, equações diferenciais lineares ordinárias, integrais de linha aplicadas a um campo escalar e a um campo vetorial, explorando técnicas de operacionalização e aplicações na resolução de problemas específicos e contextualizados; São estudados conceitos relativos as aplicações; as equações diferenciais lineares ordinárias de 1a e 2a ordem e os principais teoremas envolvendo campos vetoriais, e seu emprego na modelagem de sistemas físicos, com foco na resolução e interpretação de resultados. As dinâmicas devem privilegiar a utilização de softwares nas atividades de simulação, análise e síntese, facilitando a fixação de conceitos e o desenvolvimento do raciocínio lógico, dedutivo e abstrato.','Integrais;Derivadas','100',2,1);


INSERT INTO equivalencia_disciplina.usuario (email,nome,password,perfil) VALUES
	 ('matheusjcosta675@gmail.com','Matheus Jose - Admin','$2a$10$h.dVQ.0AJLRjZTqthgtnDeFBxgQd.XXgFe6a5cVKVFHWFiVdP6hHa',1),
	 ('matheus.costa@tutanota.com','Matheus Jose','$2a$10$ZjIsFqU51y3N2E6c1BcTluV7BqN9.Cos4AETOHaBJXb5EnNxQcs0a',2);

INSERT INTO equivalencia_disciplina.perfil (usuario_id,perfil) VALUES
	 (1,1),
	 (2,2);

INSERT INTO equivalencia_disciplina.professor (nome,faculdade_id,curso_id,disciplina_id,usuario_id) VALUES
	 ('Matheus Jose',1,2,1,2);

INSERT INTO equivalencia_disciplina.analises (data_maxima,faculdade_origem_id,curso_origem_id,disciplina_origem_id,faculdade_destino_id,curso_destino_id,disciplina_destino_id,professor_id,admin_user_id,status,nome_aluno,email_aluno) VALUES
	 ('2023-10-31',2,1,2,1,2,1,1,1,'PENDING', 'Flavio', 'fakeofcontas@gmail.com');

