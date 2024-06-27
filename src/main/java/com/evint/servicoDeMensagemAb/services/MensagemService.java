package com.evint.servicoDeMensagemAb.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.json.MensagemAuxiliar;
import com.evint.servicoDeMensagemAb.entities.json.MensagemDTO;
import com.evint.servicoDeMensagemAb.repositories.MensagemRepository;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository repository;
	
	@Autowired
	private UsuarioMensagemRepository uMRepository;
	
	@Autowired
	private UsuarioMensagemService uMService;
	
	public List<MensagemDTO> buscarMensagensNaoExcluidas(String cpf) {
		Set<MensagemDTO> set = new HashSet<>();
	
		List<Mensagem> listM = repository.retornarMensagensNaoExcluidas(cpf);
		Mensagem[] vectM = new Mensagem[listM.size()];
		listM.toArray(vectM);
		
		List<UsuarioMensagem> listUm = uMRepository.retornarUsuarioMensagensSemDataExclusao(cpf);
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
		return repository.findAll();
	}
	
	public List<Mensagem> buscarMensagensNaoLidas(Long id) {
		List<Mensagem> list = repository.buscarMensagensNaoLidas(id);
		return list;
	}
	
	public Mensagem findById(Long id) {
		Optional<Mensagem> msg = repository.findById(id); 
		return msg.get();
	}
	
	public Mensagem salvarMensagem(Mensagem msg) {
		return repository.save(msg);
	}
	
	public List<UsuarioMensagem> CriarESalvarMensagemEUsuarioMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = repository.save(instanciarMensagemDaMensagemAuxiliar(msgA));
		List<UsuarioMensagem> list = uMService.criarESalvarUsuarioMensagem(msgA, msg);
		return list;
	}

	public Mensagem instanciarMensagemDaMensagemAuxiliar(MensagemAuxiliar msgA) {
		Mensagem msg = new Mensagem();
		msg.setDescricao(msgA.getDescricao());
		msg.setTitulo(msgA.getTitulo());
		return msg;
	}	
}
