package algar.desafio.api.service.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.ProdutoRepository;
import algar.desafio.api.repository.UsuarioRepository;

@Service
public class CompraService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public UsuarioDTO compraProduto(CompraDTO compraDTO) throws Exception {
        
        Produto produto = produtoRepository.findById(compraDTO.produtoId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(compraDTO.usuarioId()).orElse(null);

        if(usuario.getAtivo() == false || usuario == null ){
            throw new ResourceNotFoundException("Usuário não cadastrado!");
        }

        if(produto == null){
            throw new ResourceNotFoundException("Produto não cadastrado!");
        }

        if(produto.getQuantidade() <= 0){
            throw new ResourceNotFoundException("Produto fora de estoque!");
        }

        if (produto.getValor() > (usuario.getSaldo())) {
            throw new Exception("Usuario não tem saldo suficiente!");
        }

        List<Produto> usuarioProduto = usuario.getProdutos();
        usuarioProduto.add(produto);
        usuario.setProdutos(usuarioProduto);

        usuario.setSaldo(usuario.getSaldo() - produto.getValor());
        produto.setQuantidade(produto.getQuantidade() - 1);

        usuarioRepository.save(usuario);
        produtoRepository.save(produto);

        return new UsuarioDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getCpf(),
            usuario.getSaldo(),
            usuario.getProdutos());
    }
}
