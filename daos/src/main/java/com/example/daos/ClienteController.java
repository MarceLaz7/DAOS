package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.Annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios");


public class UsuarioController {
	
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@GetMapping
	public List<Usuario> obtenerUsuario() {
		return UsuarioRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(usuario -> ResponseEntity.ok().body(usuario))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping // método post
	
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}
	
}
