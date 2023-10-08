package algar.desafio.api.service.usuario;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import algar.desafio.api.dto.UsuarioDTO;
import algar.desafio.api.model.Usuario;

public interface UsuarioInterface {

    void criarUsuario(Usuario usuario) throws DataIntegrityViolationException;
    // InvoiceDTO compraProduto(String cpf, TitleListDTO titleListDTO) throws Exception;
    UsuarioDTO adicionaSaldo(String cpf, double saldo) throws ResourceNotFoundException;
    UsuarioDTO getUsuario(Long id);
    
}
