## College
INSERT INTO discipline_equivalence.college (id, name) VALUES (1, 'Universidade Federal de Uberlandia');
INSERT INTO discipline_equivalence.college (id, name) VALUES (2, 'Universidade Federal de Minas Gerais');

## Course
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (1, 'Matematica', 1);
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (2, 'Engenharia Mecanica', 1);
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (3, 'Ciencia da Computacao', 1);
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (4, 'Engenharia Civil', 2);
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (5, 'Engenharia Mecanica', 2);
INSERT INTO discipline_equivalence.course (id, name, college_id) VALUES (6, 'Ciencia da Computacao', 2);

## Discipline
INSERT INTO discipline_equivalence.discipline (id, name, origin_code, menu, program, work_load, college_id, course_id) VALUES (1, "Calculo I", "FAMAT1", "Limites;Derivadas", "Limites", "60", 1, 1);
INSERT INTO discipline_equivalence.discipline (id, name, origin_code, menu, program, work_load, college_id, course_id) VALUES (2, "Programacao Procedimental", "PP0001", "Lacos;Condicionais", "Lacos", "60", 2, 3);