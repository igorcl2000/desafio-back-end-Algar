package algar.desafio.api.dto;

public class CompraDTO {
    
    private Long usuarioId;
    private Long produtoId;

    public CompraDTO() {
    }

    public CompraDTO(Long usuarioId, Long produtoId) {
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
