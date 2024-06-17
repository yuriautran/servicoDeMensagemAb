package com.evint.servicoDeMensagemAb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.services.UsuarioMensagemService;

@RestController
@RequestMapping(value = "/usuariomensagens")
public class UsuarioMensagemResource {
	
	@Autowired
	private UsuarioMensagemService service;
	
	@GetMapping
	public ResponseEntity<List<UsuarioMensagem>> findAll() {
		List<UsuarioMensagem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
} 