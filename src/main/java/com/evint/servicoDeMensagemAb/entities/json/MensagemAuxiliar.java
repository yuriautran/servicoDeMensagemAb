package com.evint.servicoDeMensagemAb.entities.json;

import java.io.Serializable;
import java.util.Objects;

public class MensagemAuxiliar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private String escopo;
	private String itens;
	
	public MensagemAuxiliar() {
		
	}

	public MensagemAuxiliar(String titulo, String descricao, String escopo, String itens) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.escopo = escopo;
		this.itens = itens;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensagemAuxiliar other = (MensagemAuxiliar) obj;
		return Objects.equals(titulo, other.titulo);
	}
}
