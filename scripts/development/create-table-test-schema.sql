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
    ementa varchar(255) not null,
    programa varchar(255) not null,
    carga_horaria varchar(255) not null,
    faculdade_id int not null,
    curso_id int not null,
    constraint FKfr6rjc04htbtc3xas2b9xmq7r
            foreign key (faculdade_id) references faculdade (id),
    constraint FKssroqj2vyiaujfleklq1ifigj
                foreign key (curso_id) references curso (id)
);


create table professor
(
    id int auto_increment
        primary key,
    nome varchar(255) not null,
    faculdade_id int not null,
    curso_id int not null,
    disciplina_id int not null,
    constraint FKfr6rjc04htbtc3xab2b9xmq7r
            foreign key (faculdade_id) references faculdade (id),
    constraint FKssroqj2vyiaujfleclq1ifigj
                foreign key (curso_id) references curso (id),
    constraint FKssroqj2vyiaujfledlq1ifigj
                    foreign key (disciplina_id) references disciplina (id)
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
            foreign key (professor_id) references professor (id)
);


create table notificacao
(
    id int auto_increment
        primary key,
    data_maxima date not null,
    analise_id int not null,
    status varchar(10) not null,
    constraint FKssroqj2vziaujabcdlq1fiigj
            foreign key (analise_id) references analises (id)
);