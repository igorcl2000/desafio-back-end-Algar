package algar.desafio.api.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(

        @NotBlank String nome,

        @NotBlank @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") String cpf,

        @NotBlank @Email String email,

        double saldo

) {

}

/*
 * Usuario
 * Id
 * nome
 * cpf uniq
 * e-mail uniq
 * saldo
 * ativo
 * 
 * Produto
 * Id
 * nome
 * desc
 * valor
 * qntd.
 * ativo
 */