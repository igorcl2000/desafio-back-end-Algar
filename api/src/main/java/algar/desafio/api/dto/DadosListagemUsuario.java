package algar.desafio.api.dto;

import algar.desafio.api.model.Usuario;

//import java.util.List;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String cpf,
        String email,
        double saldo,
        Long produtoId) {

    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSaldo(),
                usuario.getProdutoId());
    }

}
