package algar.desafio.api.dto;

import java.util.List;

import algar.desafio.api.model.Usuario;

public class ProdutoDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private int quantiade;
    private List<Usuario> usuarios;

    public ProdutoDTO(Long id, String nome, String descricao, double valor, int quantiade, List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantiade = quantiade;
        this.usuarios = usuarios;
    }

    public ProdutoDTO(String nome,List<Usuario> usuarios) {
        this.nome = nome;
        this.usuarios = usuarios;
    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantiade() {
        return this.quantiade;
    }
    public void setQuantiade(int quantiade) {
        this.quantiade = quantiade;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", valor='" + getValor() + "'" +
            ", quantidade='" + getQuantiade() + "'" +
            ", usuarios='" + getUsuarios() + "'" +
            "}";
    }

    
}
