package com.github.fa2bio.domain.exception;

public class ClientNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String uuid) {
		super(String.format("There isn't a client with code %s", uuid));
	}
	
	
}
