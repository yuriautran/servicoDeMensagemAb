package com.evint.servicoDeMensagemAb.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.entities.json.MensagemAuxiliar;
import com.evint.servicoDeMensagemAb.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.get();
	}
	
	public List<Usuario> findByUf(String uf) {
		return repository.findByUfIgnoreCase(uf);
	}
	
	public List<Usuario> buscarUsuarioPorOrgaoId(Long id) {
		return repository.buscarUsuarioPorOrgaoId(id);
	}
	
	public List<Usuario> buscarUsuarioPorNomeDoOrgao(String nome) {
		nome = nome.toUpperCase();
		return repository.buscarUsuarioPorOrgaoNome(nome);
	}

	public List<Usuario> buscarUsuarioPorTipoDeOrgao(String tipoDeOrgao) {
		tipoDeOrgao = tipoDeOrgao.toUpperCase();
		return repository.buscarUsuarioPorTipoDeOrgao(tipoDeOrgao);
	}
	
	public List<Usuario> listaDeUsuariosPorOrgaoId(MensagemAuxiliar msgA) {
		List<String> listaIds = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorOrgao = new ArrayList<>();
		
		for(String stringId : listaIds) {
			Long id = Long.parseLong(stringId);
			listaUsuarioPorOrgao.addAll(buscarUsuarioPorOrgaoId(id));
		}
		
		return listaUsuarioPorOrgao;
	}
	
	public List<Usuario> listaDeUsuariosPorNomeDoOrgao(MensagemAuxiliar msgA) {
		List<String> listaNomeDoOrgao = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorNomeDoOrgao = new ArrayList<>();
		
		for(String nomeDoOrgao : listaNomeDoOrgao) {
			listaUsuarioPorNomeDoOrgao.addAll(buscarUsuarioPorNomeDoOrgao(nomeDoOrgao));
		}
		return listaUsuarioPorNomeDoOrgao;
	}

	public List<Usuario> listaDeUsuariosPorTipoDeOrgao(MensagemAuxiliar msgA) {
		List<String> listaTipoDeOrgao = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorTipoDeOrgao = new ArrayList<>();
		
		for(String tipoDeOrgao : listaTipoDeOrgao) {
			listaUsuarioPorTipoDeOrgao.addAll(buscarUsuarioPorTipoDeOrgao(tipoDeOrgao));
		}
		return listaUsuarioPorTipoDeOrgao;
	}
	
	public List<Usuario> listaDeUsuariosPorUf(MensagemAuxiliar msgA) {
		List<String> listaUf = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorUf = new ArrayList<>();
		
		for(String uf : listaUf) {
			listaUsuarioPorUf.addAll(findByUf(uf));
		}
		return listaUsuarioPorUf;
	}
	
	public List<Usuario> listaDeUsuarioPorId(MensagemAuxiliar msgA) {
		List<String> listaIdDoUsuario = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorId = new ArrayList<>();

		for(String stringId : listaIdDoUsuario) {
			Long id = Long.parseLong(stringId);
			Usuario usuario = findById(id);
			listaUsuarioPorId.add(usuario);
		}
		return listaUsuarioPorId;
	}
}
