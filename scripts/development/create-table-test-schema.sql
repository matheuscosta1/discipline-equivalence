create table faculdade
(
    id int auto_increment primary key,
    nome varchar(255) not null
);

create table curso
(
    id int auto_increment
        primary key,
    nome varchar(255) not null,
    faculdade_id int not null,
    constraint FKgvgc4f2c3qkiswyv6bbs0br1p
            foreign key (faculdade_id) references faculdade (id)
);

create table disciplina
(
    id int auto_increment
        primary key,
    nome varchar(255) not null,
    codigo_origem varchar(255) not null,
    ementa varchar(2000) not null,
    programa varchar(2000) not null,
    carga_horaria varchar(10) not null,
    faculdade_id int not null,
    curso_id int not null,
    constraint FKfr6rjc04htbtc3xas2b9xmq7r
            foreign key (faculdade_id) references faculdade (id),
    constraint FKssroqj2vyiaujfleklq1ifigj
                foreign key (curso_id) references curso (id)
);

create table usuario
(
    id int auto_increment
        primary key,
    email varchar(100) not null,
    nome varchar(100) not null,
    password varchar(100) not null,
    perfil int not null,
    constraint UK_dwk6cx0afu8bs9o4t536v1j5v
        unique (email)
);

create table professor
(
    id int auto_increment
        primary key,
    nome varchar(255) not null,
    faculdade_id int not null,
    curso_id int not null,
    disciplina_id int not null,
    usuario_id int null,
    status varchar(15) not null,
    constraint FKfr6rjc04htbtc3xab2b9xmq7r
            foreign key (faculdade_id) references faculdade (id),
    constraint FKssroqj2vyiaujfleclq1ifigj
                foreign key (curso_id) references curso (id),
    constraint FKssroqj2vyiaujfledlq1ifigj
                    foreign key (disciplina_id) references disciplina (id),
    constraint FKssroqj2vyiaujfledlqklfigj
                        foreign key (usuario_id) references usuario (id)
);

create table analises
(
    id int auto_increment
        primary key,
    data_maxima date not null,
    faculdade_origem_id int not null,
    curso_origem_id int not null,
    disciplina_origem_id int not null,
    faculdade_destino_id int not null,
    curso_destino_id int not null,
    disciplina_destino_id int not null,
    professor_id int not null,
    admin_user_id int not null,
    status varchar(15) not null,
    email_aluno varchar(100) not null,
    nome_aluno varchar(100) not null,
    constraint FKdr6rjc04htbtc3xab2b9xmq7r
            foreign key (faculdade_origem_id) references faculdade (id),
    constraint FKssroko2vyiaujfleclq1ifigj
            foreign key (curso_origem_id) references curso (id),
    constraint FKssroqj2vziaujfledlq1ifigj
            foreign key (disciplina_origem_id) references disciplina (id),
    constraint FKdr6rjc04htbtc3xab2b9xqj7r
            foreign key (faculdade_destino_id) references faculdade (id),
    constraint FKssroko2vyiaujfleclq1ifkgj
            foreign key (curso_destino_id) references curso (id),
    constraint FKssrnbo2vyiaujfleclq1ifkgj
                foreign key (disciplina_destino_id) references disciplina (id),
    constraint FKssroqj2vziaujfledlq1fiigj
            foreign key (professor_id) references professor (id),
    constraint FKkkroqj2vziaujfledlq1fiigj
                foreign key (admin_user_id) references usuario (id)
);





create table perfil
(
	id int auto_increment
        primary key,
    usuario_id int not null,
    perfil int null,
    constraint FK88rruc7qawl75mscnnwrah9sc
        foreign key (usuario_id) references usuario (id)
);

create table equivalencia
(
    id int auto_increment
        primary key,
    justificativa varchar(2000) not null,
    equivalente BOOLEAN not null,
    analise_equivalencia_id int not null,
    data_criacao date not null,
    status varchar(15) not null,
    constraint FKfr6rlo04htbtc3xab2b9xmq7r
            foreign key (analise_equivalencia_id) references analises (id)
);

create table notificacao
(
    id int auto_increment
        primary key,
    data_maxima date not null,
    analise_id int not null,
    equivalencia_id int null,
    status varchar(15) not null,
    email varchar (50) not null,
    constraint FKssroqj2vziaujabcdlq1fiigj
            foreign key (analise_id) references analises (id),
    constraint FKssroqj2vziaujabcdlq1fkkgj
                foreign key (equivalencia_id) references equivalencia (id)
);

create table analise_equivalencia_open_ai
(
    id int auto_increment
        primary key,
    disciplina_origem_id int not null,
    disciplina_destino_id int not null,
    status varchar(15) not null,
    semelhanca varchar (1000) null,
    diferenca varchar (1000) null,
    consideracao varchar (1000) null,
    data_criacao TIMESTAMP not null,
    constraint FKmdroqj2vziaujabcdlq1fiigj
            foreign key (disciplina_origem_id) references disciplina (id),
    constraint FKmeroqj2vziaujabcdlq1fiigj
                foreign key (disciplina_destino_id) references disciplina (id)
);

create table modificacao_ementa_disciplina
(
    id int auto_increment
        primary key,
    disciplina_id int not null,
    status varchar(15) not null,
    constraint FKmeroqj2vziaukabcdlq1fiigj
                foreign key (disciplina_id) references disciplina (id)
);
