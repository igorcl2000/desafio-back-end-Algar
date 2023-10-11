package algar.desafio.api.service.usuario;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public List<Usuario> usuarioLista() {

        System.out.println("Buscando usuario...");
        simulateLatency();
        return usuarioRepository.findAllByAtivoTrue();
    }

    public Usuario getUsuario(Long id ) throws AccessDeniedException {

        System.out.println("Buscando usuario...");
        simulateLatency();
        
        Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

		if (usuario.getAtivo() == false) {
			throw new AccessDeniedException("usuario inativo!");
		}

		return usuario;
    }

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

    public UsuarioDTO alterarUsuario(Usuario usuario) throws AccessDeniedException {
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
            throw new AccessDeniedException("Usuario não ativo!");
        }
        
    }

    public Usuario desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        
        if(usuario != null && usuario.getAtivo() == true){
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return usuario;
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