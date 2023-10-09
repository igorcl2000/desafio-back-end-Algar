package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

// import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.service.compra.CompraInterface;

@Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraInterface compraInterface;

    @PostMapping()
    public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO compraDTO) throws Exception{

        UsuarioDTO compra = compraInterface.compraProduto(compraDTO);

        return ResponseEntity.ok().body(compra);

    }
}
