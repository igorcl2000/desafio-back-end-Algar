package algar.desafio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
public class ProdutoController {

    @Autowired
    private ProdutoInterface produtoInterface;

    @PostMapping(value = "/criar")
    @CacheEvict(value = "produto", allEntries = true)
    public ResponseEntity<?> criarProduto(@RequestBody @Valid Produto produto){
        Produto produtoCriado = produtoInterface.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping(value = "/lista")
    @Cacheable(value = "produto")
    public ResponseEntity<List<Produto>> ProdutoLista(){
        // List<Produto> ProdutoLista = produtoInterface.produtoLista();
        return ResponseEntity.ok().body(produtoInterface.produtoLista());
    }

    @GetMapping(value = "/")
    @Cacheable(value = "produtoID", condition = "#id > 1")
    public ResponseEntity<Produto> getProduto(@RequestParam("id") Long id){
        // Produto produto = produtoInterface.getProduto(id);
        return ResponseEntity.ok().body(produtoInterface.getProduto(id));
    }

    @PutMapping(value = "/alterar")
    @CachePut(value = "produto")
    public ResponseEntity<ProdutoDTO> alterarProduto(@RequestBody @Valid Produto produto){
        ProdutoDTO produtoDTO = produtoInterface.alteraProduto(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @DeleteMapping(value = "/desativar")
    @CachePut(value = "produto")
    public ResponseEntity<Produto> desativarUsuario(@RequestParam("id") Long id){
        produtoInterface.desativarProduto(id);
        return ResponseEntity.ok().body(null);
    }
}
