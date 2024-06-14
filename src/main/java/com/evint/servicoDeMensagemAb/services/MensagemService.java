package com.evint.servicoDeMensagemAb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.repositories.MensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository repository;
	
	
	public List<Mensagem> findAll(){
		return repository.findAll();
	}
	
	
	public Mensagem findById(Long id) {
		Optional<Mensagem> msg = repository.findById(id); 
		return msg.get();
	}
}
