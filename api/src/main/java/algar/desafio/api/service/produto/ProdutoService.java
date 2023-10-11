package algar.desafio.api.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algar.desafio.api.model.Produto;
import algar.desafio.api.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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
    public List<Produto> produtoLista() {

        System.out.println("Buscando produto...");
        simulateLatency();
        return produtoRepository.findAllByAtivoTrue();
    }

    public Produto getProduto(Long id) throws AccessDeniedException {

        System.out.println("Buscando produto...");
		// simulateLatency();

		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

		if (produto.getAtivo() == false) {
			throw new AccessDeniedException("produto inativo!");
		}

		return produto;
    }
    
    public Produto alteraProduto(Produto produto)  throws AccessDeniedException {

        Produto produtoId = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        // Produto produtoId = produtoRepository.findById(produto.getId()).orElse(null);

        if(produtoId.getAtivo() == true){
            
            if (produto.getNome() != null) {
                produtoId.setNome(produto.getNome());
            }
            if (produto.getDescricao() != null) {
                produtoId.setDescricao(produto.getDescricao());
            }
            if (produto.getValor() != 0.0) {
                produtoId.setValor(produto.getValor());
            }
            if (produto.getQuantidade() != 0) {
                produtoId.setQuantidade(produto.getQuantidade());
            }

            produtoRepository.save(produtoId);

            return produtoId;
            // return new ProdutoDTO(produtoId.getId(),produtoId.getNome(), produtoId.getDescricao(), produtoId.getValor(), produtoId.getQuantidade(), null);
        } else {
            throw new AccessDeniedException("Produto não ativo!");
        }
    }

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
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}

