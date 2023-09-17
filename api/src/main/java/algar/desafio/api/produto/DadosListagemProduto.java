package algar.desafio.api.produto;

public record DadosListagemProduto(String nome, String descricao, double valor, int quantidade) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getNome(), produto.getDescricao(), produto.getValor(), produto.getQuantidade());
    }

}
