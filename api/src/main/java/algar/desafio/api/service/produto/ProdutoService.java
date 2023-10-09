package algar.desafio.api.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ProdutoService implements ProdutoInterface{

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Override
    @Transactional
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

    @Override
    public List<Produto> produtoLista() {

        System.out.println("Buscando produto...");
        simulateLatency();
        return produtoRepository.findAllByAtivoTrue();
    }

    @Override
    public Produto getProduto(Long id) throws ResourceNotFoundException {

        System.out.println("Buscando produto...");
        simulateLatency();

        Produto produto = produtoRepository.findById(id).orElse(null);

        if(produto != null ){
            System.out.println("Produto entregue. ");
            return produto;
        }
        else{
            throw new DataIntegrityViolationException("Produto não cadastrado!");
        }
    }

    @Override
    @Transactional
    public ProdutoDTO alteraProduto(Produto produto) throws ResourceNotFoundException {
        Produto produtoId = produtoRepository.findById(produto.getId()).orElse(null);

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

            return new ProdutoDTO(produtoId.getNome(), produtoId.getDescricao(), produtoId.getValor(), produtoId.getQuantidade(), produtoId.getUsuarios());
        } else {
            throw new DataIntegrityViolationException("Produto não ativo!");
        }
    }

    @Override
    @Transactional
    public Produto desativarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        
        if(produto != null && produto.getAtivo() == true){
            produto.setAtivo(false);
            produtoRepository.save(produto);
            return produto;
        }
        else{
            throw new DataIntegrityViolationException("Produto não cadastrado!");
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

