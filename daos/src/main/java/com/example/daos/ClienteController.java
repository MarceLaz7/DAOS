package com.example.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.Annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente");


public class ClientesController {
	
	
	private final ClientesRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	@GetMapping
	public List<Cliente> obtenerCliente() {
		return ClienteRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(cliente -> ResponseEntity.ok().body(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping // método post
	
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}
	
}
