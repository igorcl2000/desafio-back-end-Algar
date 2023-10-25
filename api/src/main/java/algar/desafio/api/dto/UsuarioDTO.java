package algar.desafio.api.dto;

import java.util.List;

import algar.desafio.api.model.Produto;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private double saldo;
    private List<Produto> produtos;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String cpf, double saldo, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.saldo = saldo;
        this.produtos = produtos;
    }

    public UsuarioDTO(String nome, String cpf, double saldo) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public Long getId() {
        return this.id;
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
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return this.cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public double getSaldo() {
        return this.saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public List<Produto> getProdutos() {
        return this.produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", saldo='" + getSaldo() + "'" +
            ", produtos='" + getProdutos() + "'" +
            "}";
    }
}

