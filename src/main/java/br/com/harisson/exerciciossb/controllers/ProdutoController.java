package br.com.harisson.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.harisson.exerciciossb.model.entities.Produto;
import br.com.harisson.exerciciossb.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	/*
	 * OUTRA FORMA DE FAZER, PEGANDO OS ATRIBUTOS UM POR UM
	 *
	 * @PostMapping public @ResponseBody Produto newProduct(@RequestParam String
	 * name, @RequestParam double price, @RequestParam double sale) { Produto
	 * produto = new Produto(name, price, sale); produtoRepository.save(produto);
	 * return produto; }
	 */

	/*
	 * POSSO TER UM METODO PARA POST E PUT, PORQUE NESSE EXEMPLO ELES SAO EXATAMENTE
	 * IGUAIS
	 *
	 * @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	 * public @ResponseBody Produto newProductObject(@Valid Produto produto) {
	 * produtoRepository.save(produto); return produto; }
	 */

	@GetMapping
	public Iterable<Produto> getProducts() {
		return produtoRepository.findAll();
	}
	
	@GetMapping(path = "/name/{partName}")
	public Iterable<Produto> getProductsByName(@PathVariable String partName) {
		//return produtoRepository.findByNameContainingIgnoreCase(partName);
		return produtoRepository.searchByNameLike(partName);
	}

	@GetMapping(path = "/page/{pageNumber}/{pageAmount}")
	public Iterable<Produto> getProductsByPage(@PathVariable int pageNumber, @PathVariable int pageAmount) {
		if (pageAmount >= 5)
			pageAmount = 5;

		Pageable page = PageRequest.of(pageNumber, pageAmount);
		return produtoRepository.findAll(page);

		// Remove os dados do pageable e mostra apenas o conteudo
		// return produtoRepository.findAll(page).getContent();
	}

	@GetMapping(path = "/{id}")
	public Optional<Produto> getProductById(@PathVariable int id) {
		return produtoRepository.findById(id);
	}

	@PostMapping
	public @ResponseBody Produto newProductObject(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}

	@PutMapping
	public Produto updateProduct(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteProduct(@PathVariable int id) {
		produtoRepository.deleteById(id);

		if (produtoRepository.findById(id) != null) {
			return "Registro excluido com sucesso!";
		} else {
			return "Ocorreu um erro ao excluir o produto!";
		}

	}

}