package algar.desafio.api.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private int quantidade;

    private Boolean ativo;

    public Produto(DadosCadastroProduto dados) {
        // this.id = dados.id();
        this.ativo = true;
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.quantidade = dados.quantidade();

    }

    public void excluir() {
        this.ativo = false;
    }

    public void DadosAtualizacaoProduto(DadosAtualizacaoProduto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.valor() != 0.0) {
            this.valor = dados.valor();
        }
        if (dados.quantidade() != 0) {
            this.quantidade = dados.quantidade();
        }

    }

    public void setQuantidade(int i) {
        this.quantidade = i;
    }

}
