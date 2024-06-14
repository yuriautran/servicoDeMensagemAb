package com.evint.servicoDeMensagemAb.services;

import java.util.List;

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
}
