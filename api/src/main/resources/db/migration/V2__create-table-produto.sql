create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao varchar(150) not null unique,
    valor decimal(10, 2) default 0.00,
    quantidade int,
    ativo tinyint,

    primary key(id)

);