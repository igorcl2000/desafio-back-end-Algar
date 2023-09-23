package algar.desafio.api.usuario;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

                @NotNull Long id,
                String nome,
                String cpf,
                String email,
                double saldo,
                List<Long> itens

) {

}
