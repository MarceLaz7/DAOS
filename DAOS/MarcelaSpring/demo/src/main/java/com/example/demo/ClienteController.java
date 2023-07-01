package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.Annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes");


public class ClienteController {
	
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	@GetMapping
	public List<Cliente> obtenerClientes(Model model) {
		
		List<Cliente> clientes = ClienteRepository.findAll();
		model.addAttribute("clientes", clientes);
		return "clientes";
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id) {
		return ClienteRepository.findById(id)
				.map(cliente -> ResponseEntity.ok().body(cliente))
				.orElse(ReponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteRepository.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente)
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id) {
		return ClienteRepository.findById(id)
				.map(existingCliente -> {
					existingCliente.setNombre(cliente.getNombre());
					existingCliente.setApellido(cliente.getApellido());
					existingCliente.setDomicilio(cliente.getDomicilio());
					existingCliente.setEmail(cliente.getEmail());
					existingCliente.setFechaDeNacimiento(cliente.getFechaDeNacimiento());
					existingCliente.setNumeroDePasaporte(cliente.getNumeroDePasaporte());
					existingCliente.setFechaDeVencimientoDePasaporte(cliente.getFechaDeVencimientoDePasaporte());
					Cliente clienteActualizado = clienteRepository.save(existingCliente);
					return ResponseEntity.ok().body(clienteActualizado);
				});
				.orElse(ResponseEntity.notFound().build());
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
		return ClienteRepository.findById(id)
				.map(existingCliente -> {
					clienteRepository.delete(existingCliente);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	} // UNIQUE 
}
