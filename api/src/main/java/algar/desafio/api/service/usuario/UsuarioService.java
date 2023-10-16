package algar.desafio.api.service.usuario;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;
import algar.desafio.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CacheEvict(value = "usuario", allEntries = true)
    public UsuarioDTO criarUsuario(Usuario usuario){
        Usuario usuarioExiste = usuarioRepository.findByCpf(usuario.getCpf());

        if(usuarioExiste == null){
            usuario.setAtivo(true);
            usuarioExiste = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuarioExiste.getId(),usuarioExiste.getNome(),usuarioExiste.getEmail(),usuarioExiste.getCpf(),usuarioExiste.getSaldo(),usuarioExiste.getProdutos());
        }
        else{
            throw new DataIntegrityViolationException("Usuário já existente!");
        }
    }

    // @Cacheable(value = "usuario")
    public List<Usuario> usuarioLista() {

        System.out.println("Buscando usuario...");
        simulateLatency();
        return usuarioRepository.findAllByAtivoTrue();
    }

    @Cacheable(value = "usuario", key = "#id", condition = "#id > 1")
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

    @CachePut(value = "usuario", key = "#id")
    public Usuario adicionaSaldo(Long id, double saldo) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        if(usuario.getAtivo() == true){
            usuario.setSaldo(usuario.getSaldo() + saldo);
            usuarioRepository.save(usuario);

            return usuario;
        }
        else{
            throw new AccessDeniedException("Usuario não ativo!");
        }
    }

    @CachePut(value = "usuario", key = "#id")
    public Usuario alterarUsuario(Long id, UsuarioDTO usuarioDTO) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        if (usuario.getAtivo() == true) {
            if (usuarioDTO.getNome() != null) {
                usuario.setNome(usuarioDTO.getNome());
            }
            if (usuarioDTO.getEmail() != null) {
                usuario.setEmail(usuarioDTO.getEmail());
            }
            if (usuarioDTO.getCpf() != null) {
                usuario.setCpf(usuarioDTO.getCpf());
            }
            if (usuarioDTO.getSaldo() != 0) {
                usuario.setSaldo(usuarioDTO.getSaldo());
            }

            usuarioRepository.save(usuario);

            return usuario;
        } else {
            throw new AccessDeniedException("Usuario não ativo!");
        }
        
    }

    @CacheEvict(value = "usuario", key = "#id")
    public Usuario desativarUsuario(Long id) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        
        if(usuario.getAtivo() == true){
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
            return usuario;
        }
        else{
            throw new AccessDeniedException("Usuario não ativo!");
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