create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(150) not null unique,
    email varchar(150) not null unique,
    saldo decimal(10, 2) default 0.00,
    ativo tinyint,

    primary key(id)

);