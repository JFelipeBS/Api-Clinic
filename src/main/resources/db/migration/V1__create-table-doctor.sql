create table tb_doctor(

    id bigint not null auto_increment,
    named varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    specialty varchar(100) not null,
    logradouro varchar(100) not null,
    neighborhood varchar(100) not null,
    cep varchar(9) not null,
    complement varchar(100),
    num varchar(20),
    uf char(2) not null,
    city varchar(100) not null,
    primary key(id)

);