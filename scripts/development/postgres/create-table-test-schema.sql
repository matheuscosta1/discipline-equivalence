CREATE TABLE faculdade
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE curso
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    faculdade_id INT NOT NULL,
    CONSTRAINT fk_faculdade
        FOREIGN KEY (faculdade_id) REFERENCES faculdade(id)
);

CREATE TABLE disciplina
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    codigo_origem VARCHAR(255) NOT NULL,
    ementa VARCHAR(2000) NOT NULL,
    programa VARCHAR(2000) NOT NULL,
    carga_horaria VARCHAR(10) NOT NULL,
    faculdade_id INT NOT NULL,
    curso_id INT NOT NULL,
    CONSTRAINT fk_disciplina_faculdade
        FOREIGN KEY (faculdade_id) REFERENCES faculdade(id),
    CONSTRAINT fk_disciplina_curso
        FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE usuario
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    perfil INT NOT NULL,
    CONSTRAINT unique_email
        UNIQUE (email)
);

CREATE TABLE professor
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    faculdade_id INT NOT NULL,
    curso_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    usuario_id INT,
    status VARCHAR(15) NOT NULL,
    CONSTRAINT fk_professor_faculdade
        FOREIGN KEY (faculdade_id) REFERENCES faculdade(id),
    CONSTRAINT fk_professor_curso
        FOREIGN KEY (curso_id) REFERENCES curso(id),
    CONSTRAINT fk_professor_disciplina
        FOREIGN KEY (disciplina_id) REFERENCES disciplina(id),
    CONSTRAINT fk_professor_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE analises
(
    id SERIAL PRIMARY KEY,
    data_maxima DATE NOT NULL,
    faculdade_origem_id INT NOT NULL,
    curso_origem_id INT NOT NULL,
    disciplina_origem_id INT NOT NULL,
    faculdade_destino_id INT NOT NULL,
    curso_destino_id INT NOT NULL,
    disciplina_destino_id INT NOT NULL,
    professor_id INT NOT NULL,
    admin_user_id INT NOT NULL,
    status VARCHAR(15) NOT NULL,
    email_aluno VARCHAR(100) NOT NULL,
    nome_aluno VARCHAR(100) NOT NULL,
    CONSTRAINT fk_analises_faculdade_origem
        FOREIGN KEY (faculdade_origem_id) REFERENCES faculdade(id),
    CONSTRAINT fk_analises_curso_origem
        FOREIGN KEY (curso_origem_id) REFERENCES curso(id),
    CONSTRAINT fk_analises_disciplina_origem
        FOREIGN KEY (disciplina_origem_id) REFERENCES disciplina(id),
    CONSTRAINT fk_analises_faculdade_destino
        FOREIGN KEY (faculdade_destino_id) REFERENCES faculdade(id),
    CONSTRAINT fk_analises_curso_destino
        FOREIGN KEY (curso_destino_id) REFERENCES curso(id),
    CONSTRAINT fk_analises_disciplina_destino
        FOREIGN KEY (disciplina_destino_id) REFERENCES disciplina(id),
    CONSTRAINT fk_analises_professor
        FOREIGN KEY (professor_id) REFERENCES professor(id),
    CONSTRAINT fk_analises_admin_user
        FOREIGN KEY (admin_user_id) REFERENCES usuario(id)
);

CREATE TABLE perfil
(
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    perfil INT,
    CONSTRAINT fk_perfil_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE equivalencia
(
    id SERIAL PRIMARY KEY,
    justificativa VARCHAR(2000) NOT NULL,
    equivalente BOOLEAN NOT NULL,
    analise_equivalencia_id INT NOT NULL,
    data_criacao DATE NOT NULL,
    status VARCHAR(15) NOT NULL,
    CONSTRAINT fk_equivalencia_analise
        FOREIGN KEY (analise_equivalencia_id) REFERENCES analises(id)
);

CREATE TABLE notificacao
(
    id SERIAL PRIMARY KEY,
    data_maxima DATE NOT NULL,
    analise_id INT NOT NULL,
    equivalencia_id INT,
    status VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    CONSTRAINT fk_notificacao_analise
        FOREIGN KEY (analise_id) REFERENCES analises(id),
    CONSTRAINT fk_notificacao_equivalencia
        FOREIGN KEY (equivalencia_id) REFERENCES equivalencia(id)
);

CREATE TABLE analise_equivalencia_open_ai
(
    id SERIAL PRIMARY KEY,
    disciplina_origem_id INT NOT NULL,
    disciplina_destino_id INT NOT NULL,
    status VARCHAR(15) NOT NULL,
    semelhanca VARCHAR(1000),
    diferenca VARCHAR(1000),
    consideracao VARCHAR(1000),
    data_criacao TIMESTAMP NOT NULL,
    CONSTRAINT fk_analise_origem
        FOREIGN KEY (disciplina_origem_id) REFERENCES disciplina(id),
    CONSTRAINT fk_analise_destino
        FOREIGN KEY (disciplina_destino_id) REFERENCES disciplina(id)
);

CREATE TABLE modificacao_ementa_disciplina
(
    id SERIAL PRIMARY KEY,
    disciplina_id INT NOT NULL,
    status VARCHAR(15) NOT NULL,
    CONSTRAINT fk_modificacao_ementa_disciplina
        FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);
