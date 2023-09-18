package algar.desafio.api.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull Long id,
        String nome,
        String cpf,
        String email,
        double saldo

) {

}
