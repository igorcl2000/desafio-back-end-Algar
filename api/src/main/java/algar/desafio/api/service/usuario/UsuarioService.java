package algar.desafio.api.service.usuario;

import java.util.List;
// import java.util.ArrayList;
// import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

// import algar.desafio.api.dto.ProdutoDTO;
import algar.desafio.api.dto.UsuarioDTO;
// import algar.desafio.api.model.Produto;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.UsuarioRepository;
import algar.desafio.api.service.produto.ProdutoService;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UsuarioInterface{
    private final UsuarioRepository usuarioRepository;
    // private final ProdutoService produtoService;
    
    public UsuarioService(UsuarioRepository usuarioRepository, ProdutoService produtoService){
        this.usuarioRepository = usuarioRepository;
        // this.produtoService = produtoService;
    }

    @Override
    @Transactional
    public Usuario criarUsuario(Usuario usuario){
        Usuario usuarioB = usuarioRepository.findByCpf(usuario.getCpf());

        if(usuarioB == null){
            usuario.setAtivo(true);
            usuarioB = usuarioRepository.save(usuario);
            return usuarioB;
        }
        else{
            throw new DataIntegrityViolationException("Usuário já existente!");
        }
    }

    @Override
    public List<Usuario> usuarioLista() {
        return usuarioRepository.findAllByAtivoTrue();
    }

    @Override
    public Usuario getUsuario(Long id ) throws ResourceNotFoundException {
        
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario != null){
            return usuario;
        } else {
            throw new ResourceNotFoundException("Usuário não cadastrado!");
        }
    }

    @Override
    @Transactional
    public UsuarioDTO adicionaSaldo(Long id, double saldo) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario != null){
            usuario.setSaldo(usuario.getSaldo() + saldo);
            usuarioRepository.save(usuario);

            return new UsuarioDTO(usuario.getNome(), usuario.getCpf(), usuario.getSaldo());
        }
        else{
            throw new ResourceNotFoundException("Usuário não cadastrado!");
        }
    }

    @Override
    @Transactional
    public UsuarioDTO alterarUsuario(Usuario usuario) throws ResourceNotFoundException {
        Usuario usuarioId = usuarioRepository.findById(usuario.getId()).orElse(null);

        
        if (usuario.getNome() != null) {
            usuarioId.setNome(usuario.getNome());
        }
        if (usuario.getEmail() != null) {
            usuarioId.setEmail(usuario.getEmail());
        }
        if (usuario.getCpf() != null) {
            usuarioId.setCpf(usuario.getCpf());
        }
        if (usuario.getSaldo() != 0) {
            usuarioId.setSaldo(usuario.getSaldo());
        }

        usuarioRepository.save(usuarioId);

        return new UsuarioDTO(usuarioId.getId(), usuarioId.getNome(), usuarioId.getEmail(), usuarioId.getCpf(), usuarioId.getSaldo(), usuarioId.getProdutos());
    }

    @Override
    @Transactional
    public Usuario desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        
        if(usuario != null && usuario.getAtivo() == true){
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return usuario;
        }
        else{
            throw new DataIntegrityViolationException("Produto não cadastrado!");
        }
    }
    
}




    // @Override
    // public InvoiceDTO buyBook(String itin, TitleListDTO titleListDTO) throws Exception {
    //     float value = 0;
    //     List<Book> bookList =  new ArrayList<>();

    //     Client client = clientRepository.findByItin(itin);

    //     if(client == null){
    //         throw new ResourceNotFoundException("Usuário não cadastrado!");
    //     }

    //     for(String title : titleListDTO.getTitleList()){
    //         Book book = bookService.getBook(title);

    //         bookList.add(book);

    //         value += book.getPrice();
    //     }

    //     if(client.getBalance() >= value){
    //         client.setBalance(client.getBalance() - value);
    //         client.getBooks().addAll(bookList);

    //         clientRepository.save(client);

    //         return new InvoiceDTO(new ClientDTO(client.getName(), client.getLastName(), client.getBalance()), bookList, value);
    //     }
    //     else{
    //         throw new Exception("Saldo insuficiente para a compra!");
    //     }
    // }