package com.evint.servicoDeMensagemAb.services;

import java.time.Instant;
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
import com.evint.servicoDeMensagemAb.entities.json.MensagemDTO;
import com.evint.servicoDeMensagemAb.repositories.MensagemRepository;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository Repository;
	
	@Autowired
	private UsuarioMensagemRepository UmRepository;
	
	@Autowired
	private UsuarioService service;
	
	private UsuarioMensagem um = new UsuarioMensagem();
	
	public List<MensagemDTO> buscarMensagensNaoExcluidas(String cpf) {
		Set<MensagemDTO> set = new HashSet<>();
	
		List<Mensagem> listM = Repository.retornarMensagensNaoExcluidas(cpf);
		Mensagem[] vectM = new Mensagem[listM.size()];
		listM.toArray(vectM);
		
		List<UsuarioMensagem> listUm = UmRepository.retornarUsuarioMensagensSemDataExclusao(cpf);
		UsuarioMensagem[] vectUm = new UsuarioMensagem[listUm.size()];
		listUm.toArray(vectUm);
		
		for(int i = 0; i < vectM.length; i++) {
			for (int j = 0; j < vectUm.length; j++) {
				if(i == j) {
					MensagemDTO mDTO = new MensagemDTO(vectM[i], vectUm[j]);
					set.add(mDTO);
				}
			}
		}
		return new ArrayList<>(set); 
	}
	
	public List<Mensagem> findAll(){
		return Repository.findAll();
	}
	
	public List<Mensagem> buscarMensagensNaoLidas(Long id) {
		List<Mensagem> list = Repository.buscarMensagensNaoLidas(id);
		return list;
	}
	
	public Mensagem findById(Long id) {
		Optional<Mensagem> msg = Repository.findById(id); 
		return msg.get();
	}
	
	public Mensagem salvarMensagem(Mensagem msg) {
		return Repository.save(msg);
	}
	
	public Mensagem CriarESalvarMensagemEUsuarioMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = Repository.save(instanciarMensagemDaMensagemAuxiliar(msgA));
		Set<UsuarioMensagem> set = criarUsuarioMensagem(msgA, msg);
		UmRepository.saveAll(new ArrayList<>(set));
		return msg;
	}

	public Set<UsuarioMensagem> criarUsuarioMensagem(MensagemAuxiliar msgA, Mensagem msg) {
		List<Usuario> list = new ArrayList<>();
		Set<UsuarioMensagem> set = new HashSet<>();
		
		switch (msgA.getEscopo().toLowerCase()) {
			case "orgao":
				list = listaDeUsuariosPorOrgaoId(msgA);
				break;
			case "tipodeorgao":
				list = listaDeUsuariosPorTipoDeOrgao(msgA);
				break;
			case "uf":
				list = listaDeUsuariosPorUf(msgA);
				break;
			case "idusuario":
				list = listaDeUsuarioPorId(msgA);
				break;
			default:
				throw new IllegalArgumentException("Escopo desconhecido: " + msgA.getEscopo());
		}
		for(Usuario u : list) {
			um = new UsuarioMensagem(u, msg, Instant.now(), null, null);
			set.add(um);
		}
		return set;
	}
	
	private List<Usuario> listaDeUsuariosPorOrgaoId(MensagemAuxiliar msgA) {
		List<String> listaIds = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorOrgao = new ArrayList<>();
		
		for(String stringId : listaIds) {
			Long id = Long.parseLong(stringId);
			listaUsuarioPorOrgao.addAll(service.buscarUsuarioPorOrgaoId(id));
		}
		return listaUsuarioPorOrgao;
	}

	private List<Usuario> listaDeUsuariosPorTipoDeOrgao(MensagemAuxiliar msgA) {
		List<String> listaTipoDeOrgao = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorTipoDeOrgao = new ArrayList<>();
		
		for(String tipoDeOrgao : listaTipoDeOrgao) {
			listaUsuarioPorTipoDeOrgao.addAll(service.buscarUsuarioPorTipoDeOrgao(tipoDeOrgao));
		}
		return listaUsuarioPorTipoDeOrgao;
	}
	
	private List<Usuario> listaDeUsuariosPorUf(MensagemAuxiliar msgA) {
		List<String> listaUf = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorUf = new ArrayList<>();
		
		for(String uf : listaUf) {
			listaUsuarioPorUf.addAll(service.findByUf(uf));
		}
		return listaUsuarioPorUf;
	}
	
	private List<Usuario> listaDeUsuarioPorId(MensagemAuxiliar msgA) {
		List<String> listaIdDoUsuario = new ArrayList<>(Arrays.asList(msgA.getItens().split(",")));
		List<Usuario> listaUsuarioPorId = new ArrayList<>();

		for(String stringId : listaIdDoUsuario) {
			Long id = Long.parseLong(stringId);
			Usuario usuario = service.findById(id);
			listaUsuarioPorId.add(usuario);
		}
		return listaUsuarioPorId;
	}

	public Mensagem instanciarMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = new Mensagem();
		msg.setDescricao(msgA.getDescricao());
		msg.setTitulo(msgA.getTitulo());
		return msg;
	}	
}
