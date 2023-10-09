package algar.desafio.api.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.usuario.UsuarioInterface;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioInterface usuarioInterface;

    @PostMapping(value = "/criar")
    @CacheEvict(value = "usuario", allEntries = true)
    public ResponseEntity<Object> criarClient(@RequestBody @Valid Usuario usuario){
        Usuario usuarioCriado = usuarioInterface.criarUsuario(usuario);
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
        // List<Usuario> UsuarioLista = usuarioInterface.usuarioLista();
        return ResponseEntity.ok().body(usuarioInterface.usuarioLista());
    }

    @GetMapping(value = "/")
    @Cacheable(value = "usuarioID", condition = "#id > 1")
    public ResponseEntity<Usuario> getUsuario(@RequestParam("id") Long id){
        //Usuario usuario = usuarioInterface.getUsuario(id);
        return ResponseEntity.ok().body(usuarioInterface.getUsuario(id));
    }

    @PutMapping(value = "/alterar")
    @CachePut(value = "usuario")
    public ResponseEntity<UsuarioDTO> alterarProduto(@RequestBody @Valid Usuario usuario){
        UsuarioDTO usuarioDTO = usuarioInterface.alterarUsuario(usuario);

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping(value = "/desativar")
    @CachePut(value = "usuario")
    public ResponseEntity<Usuario> desativarUsuario(@RequestParam("id") Long id){
        usuarioInterface.desativarUsuario(id);
        return ResponseEntity.ok().body(null);
    }

}
