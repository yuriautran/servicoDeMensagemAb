package com.evint.servicoDeMensagemAb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.services.MensagemService;

@RestController
@RequestMapping(value = "/mensagens")
public class MensagemResource {
	
	@Autowired
	private MensagemService service;
	
	@GetMapping
	public ResponseEntity<List<Mensagem>> findAll() {
		List<Mensagem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Mensagem> findById(@PathVariable("id") Long id) {
		Mensagem msg = service.findById(id);
		return ResponseEntity.ok().body(msg);
	}
} 