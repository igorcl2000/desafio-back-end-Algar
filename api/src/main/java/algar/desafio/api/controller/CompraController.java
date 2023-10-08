package algar.desafio.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.CompraDTO;
// import algar.desafio.api.dto.ListaProdutoDTO;
import algar.desafio.api.dto.UsuarioDTO;
// import algar.desafio.api.model.Produto;
// import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.compra.CompraInterface;
import algar.desafio.api.service.usuario.UsuarioInterface;

@Controller
@RequestMapping("/compra")
public class CompraController {
    private final CompraInterface compraInterface;
    // private final UsuarioInterface usuarioInterface;

    public CompraController(CompraInterface compraInterface, UsuarioInterface usuarioInterface){
        this.compraInterface = compraInterface;
        // this.usuarioInterface = usuarioInterface;
    }

    @PostMapping(value = "/realizar")
    public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO compraDTO2) throws Exception{

        UsuarioDTO compra = compraInterface.compraProduto(compraDTO2);

        return ResponseEntity.ok().body(compra);

    }
}
