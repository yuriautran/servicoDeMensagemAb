package com.evint.servicoDeMensagemAb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByUfIgnoreCase(String uf);
	
	
	@Query(value = "SELECT u.ID, u.CPF, u.NOME, u.UF FROM USUARIO u"
            + " INNER JOIN USUARIO_ORGAO uo ON u.ID = uo.USUARIO_ID"
            + " WHERE uo.ORGAO_ID = :id", nativeQuery = true)
	List<Usuario> buscarUsuarioPorOrgaoId(Long id);
	
	@Query(value = "select u.ID, u.CPF, u.NOME, u.UF from USUARIO u" 
			+ " join USUARIO_ORGAO uo"
			+ " on u.ID = uo.USUARIO_ID"
			+ " join ORGAO o"
			+ " on o.ID = uo.ORGAO_ID"
			+ " where o.NOME = :nome", nativeQuery = true)
	List<Usuario> buscarUsuarioPorOrgaoNome(String nome);
}
