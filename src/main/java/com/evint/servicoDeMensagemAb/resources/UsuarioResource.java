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
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping(value = "/uf/{uf}")
	public ResponseEntity<List<Usuario>> findByUf(@PathVariable String uf) {
		List<Usuario> list = service.findByUf(uf);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/orgaos/id/{id}")
	public ResponseEntity<List<Usuario>> buscarUsuarioPorOrgaoId(@PathVariable("id") Long id) {
		List<Usuario> list = service.buscarUsuarioPorOrgaoId(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/orgaos/nome/{nome}")
	public ResponseEntity<List<Usuario>> buscarUsuarioPorNomeDoOrgao(@PathVariable("nome") String nome) {
		List<Usuario> list = service.buscarUsuarioPorNomeDoOrgao(nome);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/orgaos/tipodeorgao/{tipodeorgao}")
	public ResponseEntity<List<Usuario>> buscarUsuarioPorTipoDeOrgao(@PathVariable("tipodeorgao") String tipodeorgao) {
		List<Usuario> list = service.buscarUsuarioPorTipoDeOrgao(tipodeorgao);
		return ResponseEntity.ok().body(list);
	}
}