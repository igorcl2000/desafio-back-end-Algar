create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(150) not null unique,
    email varchar(150) not null unique,
    saldo decimal(10, 2) default 0.00,
    produto_Id bigint default null,
    ativo tinyint,

    primary key(id),

    constraint fk_itens_id_produto foreign key(produto_Id) references produtos(id)

);