package com.evint.servicoDeMensagemAb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;

@Service
public class UsuarioMensagemService {
	
	@Autowired
	private UsuarioMensagemRepository repository;
	
	
	public List<UsuarioMensagem> findAll(){
		return repository.findAll();
	}
}
