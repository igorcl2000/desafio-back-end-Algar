package algar.desafio.api.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//import algar.desafio.api.produto.Produto;
import algar.desafio.api.usuario.DadosAtualizacaoUsuario;
import algar.desafio.api.usuario.DadosCadastroUsuario;
import algar.desafio.api.usuario.DadosListagemUsuario;
import algar.desafio.api.usuario.Usuario;
import algar.desafio.api.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {

        System.out.println("Dados recebidos: " + dados);
        var usuario = new Usuario(dados);
        Usuario usuarioCpf = repository.findByCpf(dados.cpf()).orElse(null);
        Usuario usuarioEmail = repository.findByEmail(dados.email()).orElse(null);

        if (usuarioCpf == null && usuarioEmail == null) {
            repository.save(usuario);
            var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));
        } else {
            return ResponseEntity.badRequest().body("CPF ou E-mail de usuário já existente.");
        }

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listar(@PathVariable Long id) {

        var usuario = repository.getReferenceById(id);
        Usuario usuarioId = repository.findById(id).orElse(null);

        if (usuarioId != null) {
            return ResponseEntity.ok(new DadosListagemUsuario(usuario));
        } else {
            return ResponseEntity.badRequest().body("Usuario não encontrado.");
        }

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {

        var usuario = repository.getReferenceById(dados.id());
        Usuario usuarioId = repository.findById(dados.id()).orElse(null);

        if (usuarioId != null) {
            usuario.DadosAtualizacaoUsuario(dados);
            return ResponseEntity.ok(new DadosListagemUsuario(usuario));
        } else {
            return ResponseEntity.badRequest().body("Usuario não encontrado.");
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {

        var usuario = repository.getReferenceById(id);
        Usuario usuarioId = repository.findById(id).orElse(null);

        if (usuarioId != null) {
            usuario.excluir();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Usuario não encontrado.");
        }

    }

}
