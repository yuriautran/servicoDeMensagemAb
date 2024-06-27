package com.evint.servicoDeMensagemAb.resources;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.json.MensagemAuxiliar;
import com.evint.servicoDeMensagemAb.entities.json.MensagemDTO;
import com.evint.servicoDeMensagemAb.services.MensagemService;

@RestController
@RequestMapping(value = "/mensagens")
public class MensagemResource {
	
	@Autowired
	private MensagemService service;
	
	@GetMapping
	public ResponseEntity<List<Mensagem>> findAll() {
		List<Mensagem> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Mensagem> findById(@PathVariable("id") Long id) {
		Mensagem msg = service.findById(id);
		return ResponseEntity.ok().body(msg);
	}
	
	@GetMapping(value = "/naoexcluidas/cpf/{cpf}")
	public ResponseEntity<List<MensagemDTO>> buscarMensagensNaoExcluidas(@PathVariable("cpf") String cpf) {
		List<MensagemDTO> list = service.buscarMensagensNaoExcluidas(cpf);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/naolidas/usuarios/id/{id}")
	public ResponseEntity<List<Mensagem>> buscarMensagensNaoLidas(@PathVariable("id") Long id) {
		List<Mensagem> list = service.buscarMensagensNaoLidas(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<List<UsuarioMensagem>> salvarEEnviarMensagem(@RequestBody MensagemAuxiliar msgA) {
		List<UsuarioMensagem> list = service.CriarESalvarMensagemEUsuarioMensagemDaMensagemAuxiliar(msgA);
		return ResponseEntity.ok().body(list);
	}
} 