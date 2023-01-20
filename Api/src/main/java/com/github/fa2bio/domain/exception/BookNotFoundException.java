package com.github.fa2bio.domain.exception;

public class BookNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException(String uui_code) {
		super(String.format("Não existe livro de código %s", uui_code));
	}

}
