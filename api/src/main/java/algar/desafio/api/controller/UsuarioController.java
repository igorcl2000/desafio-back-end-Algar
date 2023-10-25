package algar.desafio.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.usuario.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioInterface;

    @Autowired
    private EntityManager entity;

    @PostMapping(value = "/criar")
    @Transactional
    public ResponseEntity<?> criarClient(@RequestBody @Valid Usuario usuario){
        UsuarioDTO usuarioCriado = usuarioInterface.criarUsuario(usuario);
        entity.flush();
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @PutMapping(value = "/adicionaSaldo")
    @Transactional
    public ResponseEntity<Usuario> adicionaSaldo(@RequestParam("id") Long id, @RequestParam("saldo") double saldo) throws AccessDeniedException{
        Usuario usuario = usuarioInterface.adicionaSaldo(id, saldo);
        entity.flush();
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<List<Usuario>> UsuarioLista(){
        return ResponseEntity.ok().body(usuarioInterface.usuarioLista());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) throws AccessDeniedException{
        return ResponseEntity.ok().body(usuarioInterface.getUsuario(id));
    }

    @PutMapping(value = "/alterar/{id}")
    @Transactional
    public ResponseEntity<Usuario> alterarProduto(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO)throws AccessDeniedException {
        Usuario usuario = usuarioInterface.alterarUsuario(id ,usuarioDTO);
        entity.flush();
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping(value = "/desativar/{id}")
    @Transactional
    public ResponseEntity<Usuario> desativarUsuario(@PathVariable("id") Long id) throws AccessDeniedException {
        usuarioInterface.desativarUsuario(id);
        entity.flush();
        return ResponseEntity.ok().body(null);
    }

}
