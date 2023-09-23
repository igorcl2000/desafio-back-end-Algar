package algar.desafio.api.servicos;

public class CompraRequest {
    private Long usuarioId;
    private Long produtoId;

    public CompraRequest() {
        // Construtor vazio necessário para deserialização JSON
    }

    public CompraRequest(Long usuarioId, Long produtoId) {
        this.usuarioId = usuarioId;
        this.produtoId = produtoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
