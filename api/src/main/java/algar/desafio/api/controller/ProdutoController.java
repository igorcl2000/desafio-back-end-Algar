package algar.desafio.api.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.service.produto.ProdutoService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoInterface;

    @Autowired
    private EntityManager entity;

    @PostMapping(value = "/criar")
    @Transactional
    public ResponseEntity<?> criarProduto(@RequestBody @Valid Produto produto){
        Produto produtoCriado = produtoInterface.criarProduto(produto);
        entity.flush();
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping(value = "/lista")
    public ResponseEntity<List<Produto>> ProdutoLista(){
        return ResponseEntity.ok(produtoInterface.produtoLista());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) throws AccessDeniedException{
        return ResponseEntity.ok(produtoInterface.getProduto(id));
    }

    @PutMapping(value = "/alterar/{id}")
    @Transactional
    public ResponseEntity<Produto> alterarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws AccessDeniedException{
		Produto produto = produtoInterface.alteraProduto(id, produtoDTO);
		entity.flush();
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping(value = "/desativar/{id}")
    @Transactional
    public ResponseEntity<Produto> desativarUsuario(@PathVariable("id") Long id){
        produtoInterface.desativarProduto(id);
        entity.flush();
        return ResponseEntity.ok().body(null);
    }
}
