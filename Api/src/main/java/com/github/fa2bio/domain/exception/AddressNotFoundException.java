package com.github.fa2bio.domain.exception;

public class AddressNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String cep) {
		super(String.format("Unable to find address for zipcode: %s", cep));
	}

}
