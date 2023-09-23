package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import algar.desafio.api.produto.Produto;
import algar.desafio.api.produto.ProdutoRepository;
import algar.desafio.api.servicos.CompraRequest;
import algar.desafio.api.usuario.Usuario;
import algar.desafio.api.usuario.UsuarioRepository;

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
                // Subtrai o valor da compra do saldo do usuário
                usuario.setSaldo(usuario.getSaldo() - produto.getValor());

                // Decrementa a quantidade do produto
                produto.setQuantidade(produto.getQuantidade() - 1);

                // Você pode atualizar o usuário e o produto no banco de dados aqui
                usuarioRepository.save(usuario);
                produtoRepository.save(produto);

                return ResponseEntity.ok("Compra realizada com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Saldo insuficiente ou produto fora de estoque.");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuário ou produto não encontrado.");
        }
    }
}
