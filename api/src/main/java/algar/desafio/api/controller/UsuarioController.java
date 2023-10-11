package algar.desafio.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "usuario", allEntries = true)
    @Transactional
    public ResponseEntity<Object> criarClient(@RequestBody @Valid Usuario usuario){
        Usuario usuarioCriado = usuarioInterface.criarUsuario(usuario);
        entity.flush();
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @PutMapping(value = "/adicionaSaldo")
    @CachePut(value = "usuario")
    public ResponseEntity<UsuarioDTO> adicionaSaldo(@RequestParam("id") Long id, @RequestParam("saldo") double saldo){
        UsuarioDTO usuarioDTO = usuarioInterface.adicionaSaldo(id, saldo);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @GetMapping(value = "/lista")
    @Cacheable(value = "usuario")
    public ResponseEntity<List<Usuario>> UsuarioLista(){
        return ResponseEntity.ok().body(usuarioInterface.usuarioLista());
    }

    @GetMapping(value = "/{id}")
    @Cacheable(value = "usuario", condition = "#id > 1")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) throws AccessDeniedException{
        //Usuario usuario = usuarioInterface.getUsuario(id);
        return ResponseEntity.ok().body(usuarioInterface.getUsuario(id));
    }

    @PutMapping(value = "/alterar/{id}")
    @CachePut(value = "usuario", key = "#id")
    @Transactional
    public ResponseEntity<UsuarioDTO> alterarProduto(@PathVariable Long id)throws AccessDeniedException {

        Usuario usuarioId = usuarioInterface.getUsuario(id);
        UsuarioDTO usuarioDTO = usuarioInterface.alterarUsuario(usuarioId);
        entity.flush();
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping(value = "/desativar/{id}")
    @CachePut(value = "usuario", key = "#id")
    @Transactional
    public ResponseEntity<Usuario> desativarUsuario(@PathVariable("id") Long id){
        usuarioInterface.desativarUsuario(id);
        return ResponseEntity.ok().body(null);
    }

}
