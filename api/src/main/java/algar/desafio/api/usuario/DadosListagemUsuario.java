package algar.desafio.api.usuario;

import java.util.List;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String cpf,
        String email,
        double saldo,
        List<Long> itens) {

    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSaldo(),
                usuario.getItens());
    }

}
