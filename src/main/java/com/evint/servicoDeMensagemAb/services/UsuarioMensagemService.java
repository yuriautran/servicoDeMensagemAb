package com.evint.servicoDeMensagemAb.services;

import java.time.Instant;
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

	public UsuarioMensagem marcarComoLida(Long idUsuario,Long idMensagem, boolean lida) {
		UsuarioMensagem um = repository.findById(idUsuario, idMensagem);
		
		if (lida) {
			um.setDataLeitura(Instant.now());	
		} else {
			um.setDataLeitura(null);
		}
		return repository.save(um);
	}
	
	public UsuarioMensagem excluirMensagem(Long idUsuario,Long idMensagem, boolean excluir) {
		UsuarioMensagem um = repository.findById(idUsuario, idMensagem);
		
		if (excluir) {
			um.setDataExclusao(Instant.now());	
		} 
		return repository.save(um);
	}
}
