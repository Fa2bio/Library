package com.github.fa2bio.domain.exception;

public class EmployeeNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(String uuicode) {
		super(String.format("Não existe funcionário de código %s", uuicode));
	}

}
