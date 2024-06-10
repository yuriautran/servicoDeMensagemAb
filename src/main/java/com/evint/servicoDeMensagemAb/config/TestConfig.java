package com.evint.servicoDeMensagemAb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.evint.servicoDeMensagemAb.entities.Usuario;
import com.evint.servicoDeMensagemAb.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Alexandre Verde", "063.335.211-08", "CE");
		Usuario u2 = new Usuario(null, "Breno Cinza", "916.203.341-78", "CE");
		Usuario u3 = new Usuario(null, "Caio Azul", "752.486.435-38", "SP");
		Usuario u4 = new Usuario(null, "Diego Preto", "468.383.733-19", "RJ");
		Usuario u5 = new Usuario(null, "Francisco Amarelo", "766.431.477-19", "SP");
		Usuario u6 = new Usuario(null, "Getúlio Roxo", "116.732.214-24", "CE");
		Usuario u7 = new Usuario(null, "Heitor Azul", "125.314.455-95", "SP");
		Usuario u8 = new Usuario(null, "Júlio Marrom", "393.574.919-88", "SP");
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7, u8));
	}
}
