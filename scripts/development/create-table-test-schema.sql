create table faculdade
(
    id int auto_increment primary key,
    nome varchar(255) not null
);

create table curso
(
    id int auto_increment
        primary key,
    nome varchar(255) null,
    faculdade_id int not null,
    constraint FKgvgc4f2c3qkiswyv6bbs0br1p
            foreign key (faculdade_id) references faculdade (id)
);

create table disciplina
(
    id int auto_increment
        primary key,
    nome varchar(255) null,
    codigo_origem varchar(255) null,
    ementa varchar(255) null,
    programa varchar(255) null,
    carga_horaria varchar(255) null,
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
    nome varchar(255) null,
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