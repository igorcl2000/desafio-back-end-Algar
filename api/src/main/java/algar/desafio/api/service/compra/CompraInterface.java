package algar.desafio.api.service.compra;

// import java.util.List;

// import algar.desafio.api.dto.CompraDTO;
import algar.desafio.api.dto.CompraDTO;
// import algar.desafio.api.dto.ListaProdutoDTO;
import algar.desafio.api.dto.UsuarioDTO;
// import algar.desafio.api.model.Produto;
// import algar.desafio.api.model.Usuario;

public interface CompraInterface {

    UsuarioDTO compraProduto(CompraDTO compraDTO2) throws Exception;
    
}
