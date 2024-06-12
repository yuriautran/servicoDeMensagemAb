package com.evint.servicoDeMensagemAb.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orgao")
public class Orgao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String tipoDeOrgao;
	
	@ManyToMany(mappedBy = "orgaos")
	private Set<Usuario> usuarios = new HashSet<>();
	
	public Orgao() {
		
	}

	public Orgao(Long id, String nome, String tipoDeOrgao) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoDeOrgao = tipoDeOrgao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoDeOrgao() {
		return tipoDeOrgao;
	}

	public void setTipoDeOrgao(String tipoDeOrgao) {
		this.tipoDeOrgao = tipoDeOrgao;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orgao other = (Orgao) obj;
		return Objects.equals(id, other.id);
	}
}
