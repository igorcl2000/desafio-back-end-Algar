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
        System.out.println("dados recebidos: " + dados);

        var usuario = new Usuario(dados);

        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));
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
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(dados.id());
        usuario.DadosAtualizacaoUsuario(dados);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();

        return ResponseEntity.noContent().build();
    }

}
