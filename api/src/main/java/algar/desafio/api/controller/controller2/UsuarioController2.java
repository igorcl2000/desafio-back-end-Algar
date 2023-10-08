package algar.desafio.api.controller.controller2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.usuario.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController2 {
    private final UsuarioService usuarioService;

    public UsuarioController2(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/criarUsuario")
    public ResponseEntity<Object> createClient(@RequestBody @Valid Usuario usuario){
        usuarioService.criarUsuario(usuario);

        return ResponseEntity.created(null).build();
    }

    // @PostMapping(value = "/buyBook")
    // public ResponseEntity<InvoiceDTO> buyBook(@RequestParam("itin") String itin, @RequestBody TitleListDTO titleListDto) throws Exception{
    //     InvoiceDTO invoice = clientService.buyBook(itin, titleListDto);

    //     return ResponseEntity.ok().body(invoice);
    // }

    @PatchMapping(value = "/adicionaSaldo")
    public ResponseEntity<UsuarioDTO> adicionaSaldo(@RequestParam("cpf") String cpf, @RequestParam("balance") double saldo){
        UsuarioDTO usuarioDTO = usuarioService.adicionaSaldo(cpf, saldo);

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @GetMapping(value = "/getUsuario")
    public ResponseEntity<UsuarioDTO> getUsuario(@RequestParam("id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(id);

        return ResponseEntity.ok().body(usuarioDTO);
    }
}
