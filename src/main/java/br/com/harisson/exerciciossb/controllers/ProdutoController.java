package br.com.harisson.exerciciossb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.harisson.exerciciossb.model.entities.Produto;
import br.com.harisson.exerciciossb.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/products")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	public @ResponseBody Produto newProduct(@RequestParam String name) {
		Produto produto = new Produto(name);
		produtoRepository.save(produto);
		return produto;
	}
	
}