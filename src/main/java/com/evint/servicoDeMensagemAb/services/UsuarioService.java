package com.evint.servicoDeMensagemAb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public List<Usuario> findByUf(String uf) {
		return repository.findByUfIgnoreCase(uf);
	}
	
	public List<Usuario> buscarUsuarioPorOrgaoId(Long id) {
		return repository.buscarUsuarioPorOrgaoId(id);
	}
	
	public List<Usuario> buscarUsuarioPorOrgaoNome(String nome) {
		nome = nome.toUpperCase();
		return repository.buscarUsuarioPorOrgaoNome(nome);
	}
}
