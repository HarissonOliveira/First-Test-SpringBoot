package br.com.harisson.exerciciossb.model.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.harisson.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}