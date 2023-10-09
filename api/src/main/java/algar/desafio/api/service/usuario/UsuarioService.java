package algar.desafio.api.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UsuarioInterface{

    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Override
    @Transactional
    public Usuario criarUsuario(Usuario usuario){
        Usuario usuarioExiste = usuarioRepository.findByCpf(usuario.getCpf());

        if(usuarioExiste == null){
            usuario.setAtivo(true);
            usuarioExiste = usuarioRepository.save(usuario);
            return usuarioExiste;
        }
        else{
            throw new DataIntegrityViolationException("Usuário já existente!");
        }
    }

    @Override
    public List<Usuario> usuarioLista() {

        System.out.println("Buscando produto...");
        simulateLatency();
        return usuarioRepository.findAllByAtivoTrue();
    }

    @Override
    public Usuario getUsuario(Long id ) throws ResourceNotFoundException {

        System.out.println("Buscando produto...");
        simulateLatency();
        
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario != null){
            System.out.println("Produto entregue. ");
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

        if (usuarioId.getAtivo() == true) {
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

            return new UsuarioDTO(usuarioId.getNome(), usuarioId.getCpf(), usuarioId.getSaldo());
        } else {
            throw new DataIntegrityViolationException("Usuario não ativo!");
        }
        
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
    
    private void simulateLatency() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}