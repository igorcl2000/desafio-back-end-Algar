package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import algar.desafio.api.produto.DadosAtualizacaoProduto;
import algar.desafio.api.produto.DadosCadastroProduto;
import algar.desafio.api.produto.DadosListagemProduto;
import algar.desafio.api.produto.Produto;
import algar.desafio.api.produto.ProdutoRepository;
import algar.desafio.api.usuario.DadosListagemUsuario;
//import algar.desafio.api.usuario.DadosListagemUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
        System.out.println("dados recebidos: " + dados);
        repository.save(new Produto(dados));
    }

    @GetMapping
    public Page<DadosListagemProduto> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);

    }

    @GetMapping("/{id}")
    public DadosListagemProduto listar(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return new DadosListagemProduto(produto);

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.DadosAtualizacaoProduto(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.excluir();
    }

}
