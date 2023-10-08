package algar.desafio.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

// import algar.desafio.api.dto.DadosAtualizacaoProduto;
// import algar.desafio.api.dto.DadosCadastroProduto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.Valid;
// import lombok.AllArgsConstructor;
// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Nome não pode ser vazio!")
    private String nome;
    @NotEmpty(message = "Nome não pode ser vazio!")
    private String descricao;
    private double valor;
    private int quantidade;
    

    private Boolean ativo;


    @JsonIgnore
    @ManyToMany(mappedBy = "produtos")
    private List<Usuario> usuarios;


    public Produto() {
    }



    public Produto(String nome, String descricao, double valor, int quantidade, List<Usuario> usuarios) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.usuarios = usuarios;
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


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public Boolean getAtivo() {
        return ativo;
    }


    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", valor='" + getValor() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", usuarios='" + getUsuarios() + "'" +
            "}";
    }

}
