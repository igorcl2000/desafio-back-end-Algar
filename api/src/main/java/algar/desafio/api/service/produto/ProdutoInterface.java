package algar.desafio.api.service.produto;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.model.Produto;

public interface ProdutoInterface {
    Produto criarProduto(Produto produto) throws DataIntegrityViolationException;
    List<Produto> produtoLista();
    Produto getProduto(Long id) throws ResourceNotFoundException;
    ProdutoDTO alteraProduto(Produto produto) throws ResourceNotFoundException;
    Produto desativarProduto(Long id) throws DataIntegrityViolationException;
}
