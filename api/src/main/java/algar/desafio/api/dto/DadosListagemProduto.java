package algar.desafio.api.dto;

import algar.desafio.api.model.Produto;

public record DadosListagemProduto(Long id, String nome, String descricao, double valor, int quantidade) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getValor(), produto.getQuantidade());
    }

}
