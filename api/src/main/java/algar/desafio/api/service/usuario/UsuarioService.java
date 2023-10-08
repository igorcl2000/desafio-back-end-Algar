package algar.desafio.api.service.usuario;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.UsuarioDTO;
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
    public void criarUsuario(Usuario usuario) throws DataIntegrityViolationException{
        Usuario usuarioCpf = usuarioRepository.findByCpf(usuario.getCpf());

        if(usuarioCpf == null){
            usuarioRepository.save(usuario);
        }
        else{
            throw new DataIntegrityViolationException("Usuário já existente!");
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

    @Override
    @Transactional
    public UsuarioDTO adicionaSaldo(String cpf, double saldo) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findByCpf(cpf);

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
    public UsuarioDTO getUsuario(Long id ) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario == null){
            throw new ResourceNotFoundException("Usuário não cadastrado!");
        }

        return new UsuarioDTO(usuario.get().getId(), usuario.get().getNome(), usuario.get().getEmail(), usuario.get().getCpf(), usuario.get().getSaldo(), usuario.get().getProdutos());
    }
}
