package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import algar.desafio.api.dto.DadosListagemUsuario;
import algar.desafio.api.model.Produto;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.ProdutoRepository;
import algar.desafio.api.repository.UsuarioRepository;
import algar.desafio.api.servicos.CompraRequest;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<String> realizarCompra(@RequestBody CompraRequest compraRequest) {
        Usuario usuario = usuarioRepository.findById(compraRequest.getUsuarioId()).orElse(null);
        Produto produto = produtoRepository.findById(compraRequest.getProdutoId()).orElse(null);

        if (usuario != null && produto != null) {
            if (usuario.getSaldo() >= produto.getValor() && produto.getQuantidade() > 0) {

                usuario.setSaldo(usuario.getSaldo() - produto.getValor());
                usuario.setItem(produto.getId());
                produto.setQuantidade(produto.getQuantidade() - 1);

                usuarioRepository.save(usuario);
                produtoRepository.save(produto);

                return ResponseEntity.accepted()
                        .body((new DadosListagemUsuario(usuario) + "\n \nCompra realizada com sucesso."));
            } else {
                return ResponseEntity.badRequest().body("Saldo insuficiente ou produto fora de estoque.");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário ou produto não encontrado.");
        }
    }
}
