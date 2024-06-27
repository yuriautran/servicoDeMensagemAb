package com.evint.servicoDeMensagemAb.services.exceptions;

public class NoSuchElementException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoSuchElementException(Object id) {
		super("Não encontrado usuários para o ID: " + id);
	}

}
