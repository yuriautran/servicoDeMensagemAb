package com.evint.servicoDeMensagemAb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Orgao;
import com.evint.servicoDeMensagemAb.repositories.OrgaoRepository;

@Service
public class OrgaoService {
	
	@Autowired
	private OrgaoRepository repository;
	
	
	public List<Orgao> findAll(){
		return repository.findAll();
	}
}
