package algar.desafio.api.dto;

import java.util.List;

import algar.desafio.api.model.Produto;
// import algar.desafio.api.model.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private double saldo;
    private List<Produto> produtos;


    public UsuarioDTO() {
    }


    // DTO geral
    public UsuarioDTO(Long id, String nome, String email, String cpf, double saldo, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.saldo = saldo;
        this.produtos = produtos;
    }

    // DTO para incrementar saldo
    public UsuarioDTO(String nome, String cpf, double saldo) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public List<Produto> getProdutos() {
        return produtos;
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

