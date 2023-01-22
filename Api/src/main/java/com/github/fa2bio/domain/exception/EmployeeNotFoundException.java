package com.github.fa2bio.domain.exception;

public class EmployeeNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(String uuicode) {
		super(String.format("There isn't a employee with code %s", uuicode));
	}

}
