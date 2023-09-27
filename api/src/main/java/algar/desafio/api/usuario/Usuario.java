package algar.desafio.api.usuario;

//import java.util.ArrayList;
//import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private double saldo;
    private Long produtoId;

    private Boolean ativo;

    public Usuario(DadosCadastroUsuario dados) {
        // this.id = dados.id();
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.saldo = dados.saldo();

    }

    public void excluir() {
        this.ativo = false;
    }

    public void DadosAtualizacaoUsuario(DadosAtualizacaoUsuario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if (dados.saldo() != 0.0) {
            this.saldo = dados.saldo();
        }
        if (dados.produtoId() != null) {
            this.produtoId = dados.produtoId();
        }
    }

    public void setSaldo(double novoSaldo) {
        this.saldo = novoSaldo;
    }

    public void setItem(Long produtoId2) {
        this.produtoId = produtoId2; // Atualiza a lista de itens
    }

}
