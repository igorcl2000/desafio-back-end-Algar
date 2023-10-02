package algar.desafio.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import algar.desafio.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findAllByAtivoTrue(Pageable paginacao);

}
