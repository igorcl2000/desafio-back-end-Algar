package algar.desafio.api.controller.controller2;

import java.util.List;

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

import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.service.produto.ProdutoInterface;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController2 {
    private final ProdutoInterface produtoInterface;

    public ProdutoController2(ProdutoInterface produtoInterface){
        this.produtoInterface = produtoInterface;
    }

    @PostMapping(value = "/criarProduto")
    public ResponseEntity<?> createBook(@RequestBody @Valid Produto produto){
        Produto produtoCriado = produtoInterface.criarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping(value = "/ProdutoLista")
    public ResponseEntity<List<Produto>> ProdutoLista(){
        List<Produto> ProdutoLista = produtoInterface.produtoLista();

        return ResponseEntity.ok().body(ProdutoLista);
    }

    @GetMapping(value = "/getProduto")
    public ResponseEntity<Produto> getBook(@RequestParam("id") Long id){
        Produto produto = produtoInterface.getProduto(id);

        return ResponseEntity.ok().body(produto);
    }

    @PutMapping(value = "/alterarProduto")
    public ResponseEntity<ProdutoDTO> alterarProduto(@RequestBody @Valid Produto produto){
        ProdutoDTO produto1 = produtoInterface.alteraProduto(produto);

        return ResponseEntity.ok().body(produto1);
    }

    @DeleteMapping(value = "/desativarProduto")
    public ResponseEntity<Produto> desativarUsuario(@RequestParam("id") Long id){
        produtoInterface.desativarProduto(id);

        return ResponseEntity.ok().body(null);
    }
}
