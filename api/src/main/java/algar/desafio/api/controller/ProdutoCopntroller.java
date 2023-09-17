package algar.desafio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import algar.desafio.api.produto.DadosCadastroProduto;
import algar.desafio.api.produto.DadosListagemProduto;
import algar.desafio.api.produto.Produto;
import algar.desafio.api.produto.ProdutoRepository;
import algar.desafio.api.usuario.DadosListagemUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoCopntroller {

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
        return repository.findAll(paginacao).map(DadosListagemProduto::new);

    }

}
