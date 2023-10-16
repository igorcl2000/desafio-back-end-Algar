package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.service.compra.CompraService;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraInterface;

    @PostMapping()
    @Transactional
    public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO compraDTO) throws Exception {
        Usuario compra = compraInterface.compraProduto(compraDTO);
        return ResponseEntity.ok().body(compra);

    }
}
