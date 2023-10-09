package algar.desafio.api.service.compra;

import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.UsuarioDTO;

public interface CompraInterface {

    UsuarioDTO compraProduto(CompraDTO compraDTO) throws Exception;
    
}
