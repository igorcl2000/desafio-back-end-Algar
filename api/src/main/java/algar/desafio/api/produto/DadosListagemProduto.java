package algar.desafio.api.produto;

public record DadosListagemProduto(Long id, String nome, String descricao, double valor, int quantidade) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getValor(), produto.getQuantidade());
    }

}
