package algar.desafio.api.service.compra;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Produto;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.ProdutoRepository;
import algar.desafio.api.repository.UsuarioRepository;
import algar.desafio.api.service.produto.ProdutoInterface;

@Service
public class CompraService implements CompraInterface {
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public CompraService(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, ProdutoInterface produtoInterface){
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public UsuarioDTO compraProduto(CompraDTO compraDTO2) throws Exception {
        

        Produto produto = produtoRepository.findById(compraDTO2.produtoId()).orElse(null);

        Usuario usuario = usuarioRepository.findById(compraDTO2.usuarioId()).orElse(null);

        if(usuario == null){
            throw new ResourceNotFoundException("Usuário não cadastrado!");
        }

        if(produto == null){
            throw new ResourceNotFoundException("Produto não cadastrado!");
        }

        if(produto.getQuantidade() <= 0){
            throw new ResourceNotFoundException("Produto fora de estoque!");
        }

        if (produto.getValor() > (usuario.getSaldo())) {
            throw new ResourceNotFoundException("Usuario não tem saldo suficiente!");
        }

        List<Produto> usuarioProduto = usuario.getProdutos();
        usuarioProduto.add(produto);
        usuario.setProdutos(usuarioProduto);

        usuario.setSaldo(usuario.getSaldo() - produto.getValor());
        produto.setQuantidade(produto.getQuantidade() - 1);

        usuarioRepository.save(usuario);
        produtoRepository.save(produto);

        UsuarioDTO usuarioDTO = new UsuarioDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getCpf(),
            usuario.getSaldo(),
            usuario.getProdutos());

        return usuarioDTO;
    }




}
