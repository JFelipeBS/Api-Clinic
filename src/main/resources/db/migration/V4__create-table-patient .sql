create table tb_patient(

    id bigint not null auto_increment,
    named varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    phone varchar(20) not null,
    logradouro varchar(100) not null,
    neighborhood varchar(100) not null,
    cep varchar(9) not null,
    complement varchar(100),
    num varchar(20),
    uf char(2) not null,
    city varchar(100) not null,
    primary key(id)

);