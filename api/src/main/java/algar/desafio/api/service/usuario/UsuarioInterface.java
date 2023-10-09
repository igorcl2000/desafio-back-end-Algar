package algar.desafio.api.service.usuario;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;

public interface UsuarioInterface {

    Usuario criarUsuario(Usuario usuario) throws DataIntegrityViolationException;
    UsuarioDTO adicionaSaldo(Long id, double saldo) throws ResourceNotFoundException;
    Usuario getUsuario(Long id) throws ResourceNotFoundException;;
    List<Usuario> usuarioLista();
    UsuarioDTO alterarUsuario(Usuario usuario) throws ResourceNotFoundException;;
    Usuario desativarUsuario(Long id) throws ResourceNotFoundException;
    
}
