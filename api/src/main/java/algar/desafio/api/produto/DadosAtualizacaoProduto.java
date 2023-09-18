package algar.desafio.api.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(

        @NotNull Long id,
        String nome,
        String descricao,
        double valor,
        int quantidade

) {

}
