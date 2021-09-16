package br.com.harisson.exerciciossb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.harisson.exerciciossb.models.Cliente;

@RestController
@RequestMapping(path = "/clientes")
//@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/qualquer")
	public Cliente getCliente() {
		return new Cliente(28, "Pedro", "123.456.789-00");
	}
	
	@GetMapping("/{id}")
	public Cliente getById(@PathVariable int id) {
		return new Cliente(id, "Maria", "987.654.321-00");
	}

}