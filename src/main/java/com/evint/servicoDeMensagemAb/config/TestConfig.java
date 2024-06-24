package com.evint.servicoDeMensagemAb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.Orgao;
import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.repositories.MensagemRepository;
import com.evint.servicoDeMensagemAb.repositories.OrgaoRepository;
import com.evint.servicoDeMensagemAb.repositories.UsuarioMensagemRepository;
import com.evint.servicoDeMensagemAb.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private OrgaoRepository orgaoRepository;
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private UsuarioMensagemRepository usuarioMensagem;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Alexandre Verde", "06333521108", "CE");
		Usuario u2 = new Usuario(null, "Breno Cinza", "91620334178", "CE");
		Usuario u3 = new Usuario(null, "Caio Azul", "75248643538", "SP");
		Usuario u4 = new Usuario(null, "Diego Preto", "46838373319", "RJ");
		Usuario u5 = new Usuario(null, "Francisco Amarelo", "76643147719", "SP");
		Usuario u6 = new Usuario(null, "Getúlio Roxo", "11673221424", "CE");
		Usuario u7 = new Usuario(null, "Heitor Azul", "12531445595", "SP");
		Usuario u8 = new Usuario(null, "Júlio Marrom", "39357491988", "SP");
		
		Orgao o1 = new Orgao(null, "PMCE", "SEGURANCA_PUBLICA");
		Orgao o2 = new Orgao(null, "SEFAZ-CE", "FAZENDARIO");
		Orgao o3 = new Orgao(null, "PMSP", "SEGURANCA_PUBLICA");
		Orgao o4 = new Orgao(null, "ABIN", "INTELIGENCIA");
		
		Mensagem m1 = new Mensagem(null, "Manutenção no servidor", "Dia 03/07/2024 o sistema ficará inoperante para menutenção no servidor");
		Mensagem m2 = new Mensagem(null, "Acesso indevido", "Identificamos acesso indevido para usuários do orgão ABIN");
		Mensagem m3 = new Mensagem(null, "Atualização do sistema", "A partir de 01/07/2024, o Alerta Brasil contará com nova funcionalidade, aguardem");
		Mensagem m4 = new Mensagem(null, "Exclusão de acesso", "O estado de SP não terá mais acesso ao sistema a partir de 01/07/2024. Motivo: fim do convênio");
		Mensagem m5 = new Mensagem(null, "Atenção usuários SEFAZ-CE", "A PRF está solicitando nova documentação para acesso ao sistema. Atentem para seus email");
	
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8));
		orgaoRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		mensagemRepository.saveAll(Arrays.asList(m1, m2, m3, m4, m5));
		
		UsuarioMensagem um1 = new UsuarioMensagem(u1, m1, Instant.parse("2024-06-20T12:30:00Z"), Instant.parse("2024-06-20T12:30:00Z"), null);
		UsuarioMensagem um2 = new UsuarioMensagem(u2, m1, Instant.parse("2024-06-20T12:35:00Z"), Instant.parse("2024-06-21T12:36:00Z"), null);
		UsuarioMensagem um3 = new UsuarioMensagem(u6, m1, Instant.parse("2024-06-20T08:00:00Z"), Instant.parse("2024-06-21T09:50:00Z"), Instant.parse("2024-06-21T10:00:00Z"));
		UsuarioMensagem um4 = new UsuarioMensagem(u3, m2, Instant.parse("2024-06-20T08:00:00Z"), Instant.parse("2024-06-22T09:20:00Z"), Instant.parse("2024-06-23T12:45:00Z"));
		UsuarioMensagem um5 = new UsuarioMensagem(u4, m3, Instant.parse("2024-06-20T18:00:00Z"), null, null);
		UsuarioMensagem um6 = new UsuarioMensagem(u1, m3, Instant.parse("2024-06-25T17:00:00Z"), Instant.parse("2024-06-25T17:05:00Z"), null);
		UsuarioMensagem um7 = new UsuarioMensagem(u3, m3, Instant.parse("2024-06-20T08:00:00Z"), null, null);
		UsuarioMensagem um8 = new UsuarioMensagem(u3, m4, Instant.parse("2024-06-20T08:00:00Z"), null, null);
		UsuarioMensagem um9 = new UsuarioMensagem(u5, m4, Instant.parse("2024-06-20T09:50:00Z"), null, null);
		UsuarioMensagem um10 = new UsuarioMensagem(u7, m4, Instant.parse("2024-06-20T08:00:00Z"), Instant.parse("2024-06-20T08:10:00Z"), null);
		UsuarioMensagem um11 = new UsuarioMensagem(u8, m4, Instant.parse("2024-06-15T08:00:00Z"), null, null);
		UsuarioMensagem um12 = new UsuarioMensagem(u6, m5, Instant.parse("2024-06-15T08:00:00Z"), null, null);
		UsuarioMensagem um13 = new UsuarioMensagem(u8, m5, Instant.parse("2024-06-15T08:00:00Z"), null, null);
		
		usuarioMensagem.saveAll(Arrays.asList(um1, um2, um3, um4, um5, um6, um7, um8, um9, um10, um11, um12, um13));
		
		u1.getOrgaos().add(o1);
		u2.getOrgaos().add(o1);
		u2.getOrgaos().add(o2);
		u3.getOrgaos().add(o4);
		u4.getOrgaos().add(o4);
		u5.getOrgaos().add(o3);
		u6.getOrgaos().add(o2);
		u7.getOrgaos().add(o3);
		u8.getOrgaos().add(o2);
		u8.getOrgaos().add(o3);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8));	
	}
}
