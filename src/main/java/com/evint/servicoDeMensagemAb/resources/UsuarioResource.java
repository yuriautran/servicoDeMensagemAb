package com.evint.servicoDeMensagemAb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{uf}")
	public ResponseEntity<List<Usuario>> findByUf(@PathVariable String uf) {
		List<Usuario> list = service.findByUf(uf);
		return ResponseEntity.ok().body(list);
	}
	
}