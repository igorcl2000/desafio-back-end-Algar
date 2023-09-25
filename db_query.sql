
drop database dbdesafio;
create database dbdesafio;

show tables;

describe usuarios;
select * from usuarios;

describe produtos;
select * from produtos;

delete from flyway_schema_history where success = 0;

ALTER TABLE usuarios
MODIFY COLUMN itens INT;



ALTER TABLE usuarios
ADD CONSTRAINT fk_usuarios_itens_produto
FOREIGN KEY (itens)
REFERENCES produtos(id);
