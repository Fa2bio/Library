package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.BookInput;
import com.github.fa2bio.domain.model.Client;
import com.github.fa2bio.domain.model.Book;

@Component
public class BookInpuDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Book toDomainObject(BookInput bookInput) {
		return modelMapper.map(bookInput, Book.class);
	}
	
	public void copyToDomainObject(BookInput bookInput, Book book) {
		book.setClient(new Client());
		modelMapper.map(bookInput, book);
	}
}
