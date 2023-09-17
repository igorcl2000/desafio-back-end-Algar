package algar.desafio.api.usuario;

public record DadosListagemUsuario(String nome, String cpf, String email, double saldo) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSaldo());
    }

}
