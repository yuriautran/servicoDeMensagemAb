package com.evint.servicoDeMensagemAb.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.json.MensagemAuxiliar;
import com.evint.servicoDeMensagemAb.repositories.MensagemRepository;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository Repository;
	
	@Autowired
	private UsuarioMensagemRepository UmRepository;
	
	private MensagemAuxiliar msgA = new MensagemAuxiliar();
	
	@Autowired
	private UsuarioService service;
	
	private UsuarioMensagem um = new UsuarioMensagem();
		
	public List<Mensagem> findAll(){
		return Repository.findAll();
	}
	
	
	public Mensagem findById(Long id) {
		Optional<Mensagem> msg = Repository.findById(id); 
		return msg.get();
	}
	
	public Mensagem salvarMensagem(Mensagem msg) {
		return Repository.save(msg);
	}
	
	public Mensagem salvarMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = Repository.save(instanciarMensagemDaMensagemAuxiliar(msgA));
		distribuirParaUsuarios(msgA, msg);
		return msg;
	}

	public void distribuirParaUsuarios(MensagemAuxiliar msgA, Mensagem msg) {
		Set<UsuarioMensagem> set = new HashSet<>();
	
		if(msgA.getEscopo().equalsIgnoreCase("orgao")) {
			List<String> listaIds = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
			
			for(String stringId : listaIds) {
				Long id = Long.parseLong(stringId);
				List<Usuario> listaUsuario = service.buscarUsuarioPorOrgaoId(id);
				
				for(Usuario u : listaUsuario) {
					um = new UsuarioMensagem(u, msg, Instant.now(), null, null);
					set.add(um);
				}
			}
		}
		
		if(msgA.getEscopo().equalsIgnoreCase("tipoDeOrgao")) {
			List<String> listaTipoDeOrgao = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
			
			for(String tipoDeOrgao : listaTipoDeOrgao) {
				List<Usuario> listaUsuario = service.buscarUsuarioPorTipoDeOrgao(tipoDeOrgao);
			
				for(Usuario u : listaUsuario) {
					um = new UsuarioMensagem(u, msg, Instant.now(), null, null);
					set.add(um);
				}
			}
		}
		
		if(msgA.getEscopo().equalsIgnoreCase("uf")) {
			List<String> listaUf = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
			
			for(String uf : listaUf) {
				List<Usuario> listaUsuario = service.findByUf(uf);
			
				for(Usuario u : listaUsuario) {
					um = new UsuarioMensagem(u, msg, Instant.now(), null, null);
					set.add(um);
				}
			}
		}
		
		UmRepository.saveAll(new ArrayList<>(set));
	}
	
	public Mensagem instanciarMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = new Mensagem();
		msg.setDescricao(msgA.getDescricao());
		msg.setTitulo(msgA.getTitulo());
		return msg;
	}	
}
