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
import com.evint.servicoDeMensagemAb.entities.DTO.MensagemParaSalvarECriarUsuarioMensagem;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;
import com.evint.servicoDeMensagemAb.services.exceptions.ResourceNotFoundException;

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
		if(um == null) {
			throw new ResourceNotFoundException("usuarioMensagem inexistente para iDUsuario (" + idUsuario + ") e idMensagem ("+ idMensagem + ")");
			
		} else if (lida && um.getDataLeitura() != null) {
			throw new IllegalArgumentException("Mensagem já lida pelo usuário em: " + um.getDataLeitura());
			
		} else if (lida && um.getDataLeitura() == null) {
			um.setDataLeitura(Instant.now());
			
		} else if (!lida) {
			um.setDataLeitura(null);
		}
		return repository.save(um);
	}
	
	public UsuarioMensagem excluirMensagem(Long idUsuario,Long idMensagem) {
		UsuarioMensagem um = repository.findById(idUsuario, idMensagem);
		if(um == null) {
			throw new ResourceNotFoundException("usuarioMensagem inexistente para iDUsuario (" + idUsuario + ") e idMensagem ("+ idMensagem + ")");
		
		} else if (um.getDataExclusao() != null) {
			throw new IllegalArgumentException("Mensagem já excluída pelo usuário em: " + um.getDataExclusao());
			
		} else if(um.getDataLeitura() == null) {
			throw new IllegalArgumentException("Mensagem ainda não lida pelo usuário.");
			
		} else if (um.getDataExclusao() == null) {
			um.setDataExclusao(Instant.now());
		}
		return repository.save(um);
	}
	
	public List<UsuarioMensagem> buscarUsuarios(MensagemParaSalvarECriarUsuarioMensagem msgA, Mensagem msg) {
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
		return new ArrayList<>(set);
	}
}
