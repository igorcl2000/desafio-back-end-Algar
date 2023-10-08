package algar.desafio.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Produto;
// import algar.desafio.api.model.Produto;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.usuario.UsuarioInterface;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioInterface usuarioInterface;


    public UsuarioController(UsuarioInterface usuarioInterface){
        this.usuarioInterface = usuarioInterface;
    }

    @PostMapping(value = "/criarUsuario")
    public ResponseEntity<Object> createClient(@RequestBody @Valid Usuario usuario){
        Usuario usuarioCriado = usuarioInterface.criarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    // @PostMapping(value = "/buyBook")
    // public ResponseEntity<InvoiceDTO> buyBook(@RequestParam("itin") String itin, @RequestBody TitleListDTO titleListDto) throws Exception{
    //     InvoiceDTO invoice = clientService.buyBook(itin, titleListDto);

    //     return ResponseEntity.ok().body(invoice);
    // }

    @PutMapping(value = "/adicionaSaldo")
    public ResponseEntity<UsuarioDTO> adicionaSaldo(@RequestParam("id") Long id, @RequestParam("saldo") double saldo){
        UsuarioDTO usuarioDTO = usuarioInterface.adicionaSaldo(id, saldo);

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @GetMapping(value = "/usuarioLista")
    public ResponseEntity<List<Usuario>> UsuarioLista(){
        List<Usuario> UsuarioLista = usuarioInterface.usuarioLista();

        return ResponseEntity.ok().body(UsuarioLista);
    }

    @GetMapping(value = "/getUsuario")
    public ResponseEntity<Usuario> getUsuario(@RequestParam("id") Long id){
        Usuario usuario = usuarioInterface.getUsuario(id);

        return ResponseEntity.ok().body(usuario);
    }

    @PutMapping(value = "/alterarUsuario")
    public ResponseEntity<UsuarioDTO> alterarProduto(@RequestBody @Valid Usuario usuario){
        UsuarioDTO usuarioDTO = usuarioInterface.alterarUsuario(usuario);

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping(value = "/desativarUsuario")
    public ResponseEntity<Usuario> desativarUsuario(@RequestParam("id") Long id){
        usuarioInterface.desativarUsuario(id);

        return ResponseEntity.ok().body(null);
    }

}
