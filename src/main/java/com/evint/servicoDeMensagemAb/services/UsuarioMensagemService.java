package com.evint.servicoDeMensagemAb.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.json.MensagemAuxiliar;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;

@Service
public class UsuarioMensagemService {
	
	@Autowired
	private UsuarioMensagemRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
	
	public List<UsuarioMensagem> criarESalvarUsuarioMensagem(MensagemAuxiliar msgA, Mensagem msg) {
		List<Usuario> list = new ArrayList<>();
		Set<UsuarioMensagem> set = new HashSet<>();
		
		switch (msgA.getEscopo().toUpperCase()) {
			case "ORGAOID":
				list = usuarioService.listaDeUsuariosPorOrgaoId(msgA);
				break;
			case "NOMEDOORGAO":
				list = usuarioService.listaDeUsuariosPorNomeDoOrgao(msgA);
				break;
			case "TIPODEORGAO":
				list = usuarioService.listaDeUsuariosPorTipoDeOrgao(msgA);
				break;
			case "UF":
				list = usuarioService.listaDeUsuariosPorUf(msgA);
				break;
			case "USUARIOID":
				list = usuarioService.listaDeUsuarioPorId(msgA);
				break;
			default:
				throw new IllegalArgumentException("Escopo não encontrado: " + msgA.getEscopo().toUpperCase());
		}
		for(Usuario u : list) {
			UsuarioMensagem um = new UsuarioMensagem(u, msg, Instant.now(), null, null);
			set.add(um);
		}
		return repository.saveAll(new ArrayList<>(set));
	}
}
