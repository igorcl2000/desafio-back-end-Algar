package algar.desafio.api.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @CacheEvict(value = "produto", allEntries = true)
    public Produto criarProduto(Produto produto) {
        Produto produtoNome = produtoRepository.findByNome(produto.getNome());
        
        if(produtoNome == null){
            produto.setAtivo(true);
            produtoNome = produtoRepository.save(produto);
            return produtoNome;
        }
        else{
            throw new DataIntegrityViolationException("Produto já cadastrado!");
        }
    }

    // @Cacheable(value = "produtoLista")
    public List<Produto> produtoLista() {

        System.out.println("Buscando produto...");
        simulateLatency();
        return produtoRepository.findAllByAtivoTrue();
    }

    @Cacheable(value = "produto", key = "#id", condition = "#id > 1")
    public Produto getProduto(Long id) throws AccessDeniedException {

        System.out.println("Buscando produto...");
		simulateLatency();

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

		if (produto.getAtivo() == false) {
			throw new AccessDeniedException("produto inativo!");
		}

		return produto;
    }
    
    @CachePut(value = "produto", key = "#id") // Atualiza o cache com o produto alterado
    public Produto alteraProduto(Long id, ProdutoDTO produtoDTO)  throws AccessDeniedException {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if(produto.getAtivo() == true){
            
            if (produtoDTO.getNome() != null) {
                produto.setNome(produtoDTO.getNome());
            }
            if (produtoDTO.getDescricao() != null) {
                produto.setDescricao(produtoDTO.getDescricao());
            }
            if (produtoDTO.getValor() != 0.0) {
                produto.setValor(produtoDTO.getValor());
            }
            if (produtoDTO.getQuantidade() != 0) {
                produto.setQuantidade(produtoDTO.getQuantidade());
            }

            produtoRepository.save(produto);

            return produto;
        } else {
            throw new AccessDeniedException("Produto não ativo!");
        }
    }

    @CacheEvict(value = "produto", key = "#id")
    public Produto desativarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        
        if(produto != null && produto.getAtivo() == true){
            produto.setAtivo(false);
            produtoRepository.save(produto);
            return produto;
        }
        else{
            throw new EntityNotFoundException("Produto não cadastrado!");
        }
    }

    private void simulateLatency() {
		try {
			long time = 1000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}

