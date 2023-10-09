create table usuario_produto(

    usuario_fk bigint REFERENCES produtos(id),
    produto_fk bigint REFERENCES usuarios(id)

);