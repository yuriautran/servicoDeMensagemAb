package com.evint.servicoDeMensagemAb.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String err) {
		super (err);
	}

}
