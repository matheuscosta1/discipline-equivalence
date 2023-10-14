## faculdade
INSERT INTO equivalencia_disciplina.faculdade (id, nome) VALUES (1, 'Universidade Federal de Uberlandia');
INSERT INTO equivalencia_disciplina.faculdade (id, nome) VALUES (2, 'Universidade Federal de Minas Gerais');

## curso
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (1, 'Matematica', 1);
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (2, 'Engenharia Mecanica', 1);
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (3, 'Ciencia da Computacao', 1);
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (4, 'Engenharia Civil', 2);
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (5, 'Engenharia Mecanica', 2);
INSERT INTO equivalencia_disciplina.curso (id, nome, faculdade_id) VALUES (6, 'Ciencia da Computacao', 2);

## disciplina
INSERT INTO equivalencia_disciplina.disciplina (id, nome, codigo_origem, ementa, programa, carga_horaria, faculdade_id, curso_id) VALUES (1, "Calculo I", "FAMAT1", "Limites;Derivadas", "Limites", "60", 1, 1);
INSERT INTO equivalencia_disciplina.disciplina (id, nome, codigo_origem, ementa, programa, carga_horaria, faculdade_id, curso_id) VALUES (2, "Programacao Procedimental", "PP0001", "Lacos;Condicionais", "Lacos", "60", 2, 3);

#professor

INSERT INTO equivalencia_disciplina.professor (id, nome, faculdade_id, curso_id, disciplina_id, email) VALUES (1, "André", 1, 1, 1, "andre@teste.com");

