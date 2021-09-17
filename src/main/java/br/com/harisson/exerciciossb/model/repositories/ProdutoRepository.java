package br.com.harisson.exerciciossb.model.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.harisson.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

	public Iterable<Produto> findByNameContainingIgnoreCase(String partName);
	
	/*
	 * findByNameContaining
	 * findByNameIsContaining
	 * findByNameContains
	 * 
	 * findByNameStartsWith
	 * findByNameEndsWith
	 * 
	 * findByNameNotContaining
	 */
	
	@Query("SELECT p FROM Produto p WHERE p.name LIKE %:name%")
	public Iterable<Produto> searchByNameLike(@Param("name") String name);
	
}