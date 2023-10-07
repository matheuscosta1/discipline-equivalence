create table college
(
    id int auto_increment primary key,
    name varchar(255) not null
);

create table course
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    college_id int not null,
    constraint FKgvgc4f2c3qkiswyv6bbs0br1p
            foreign key (college_id) references college (id)
);

create table discipline
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    origin_code varchar(255) null,
    menu varchar(255) null,
    program varchar(255) null,
    work_load varchar(255) null,
    college_id int not null,
    course_id int not null,
    constraint FKfr6rjc04htbtc3xas2b9xmq7r
            foreign key (college_id) references college (id),
    constraint FKssroqj2vyiaujfleklq1ifigj
                foreign key (course_id) references course (id)
);