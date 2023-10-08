package algar.desafio.api.repository;

// import java.util.Optional;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import algar.desafio.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByCpf(@Param("cpf") String cpf);

    // Usuario findAllByAtivoTrue(boolean ativo);

    // Optional<Usuario> findByCpf(String cpf);

    // Optional<Usuario> findByEmail(String email);

}
