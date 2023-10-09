package algar.desafio.api.repository;

import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import algar.desafio.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByNome(@Param("nome") String nome);
    List<Produto> findAllByAtivoTrue();

}
