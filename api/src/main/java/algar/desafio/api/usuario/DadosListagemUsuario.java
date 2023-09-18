package algar.desafio.api.usuario;

public record DadosListagemUsuario(Long id, String nome, String cpf, String email, double saldo) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSaldo());
    }

}
