package algar.desafio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(

                @NotBlank String nome,

                @NotBlank String descricao,

                @NotNull double valor,

                @NotNull Integer quantidade

) {

}
