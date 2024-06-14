package com.evint.servicoDeMensagemAb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evint.servicoDeMensagemAb.entities.Orgao;
import com.evint.servicoDeMensagemAb.services.OrgaoService;

@RestController
@RequestMapping(value = "/orgaos")
public class OrgaoResource {
	
	@Autowired
	private OrgaoService service;
	
	@GetMapping
	public ResponseEntity<List<Orgao>> findAll() {
		List<Orgao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Orgao> findById(@PathVariable("id") Long id) {
		Orgao org = service.findById(id);
		return ResponseEntity.ok().body(org);
	}
	
	@GetMapping(value = "/{nome}")
	public ResponseEntity<Orgao> findByNomeOrgao(@PathVariable String nome) {
		Orgao org = service.findByNomeOrgao(nome);
		return ResponseEntity.ok().body(org);
	}
	
	
	
}
