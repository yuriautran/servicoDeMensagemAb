package com.evint.servicoDeMensagemAb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.Orgao;
import com.evint.servicoDeMensagemAb.repositories.OrgaoRepository;

@Service
public class OrgaoService {
	
	@Autowired
	private OrgaoRepository repository;
	
	
	public List<Orgao> findAll(){
		return repository.findAll();
	}
	
	public Orgao findById(Long id) {
		Optional<Orgao> org = repository.findById(id); 
		return org.get();
	}
	
	public Orgao findByNomeOrgao(String nome){
		return repository.findByNomeIgnoreCase(nome);
	}
}
