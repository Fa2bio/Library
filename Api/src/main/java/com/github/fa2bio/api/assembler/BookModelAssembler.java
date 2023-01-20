package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.BookModel;
import com.github.fa2bio.api.model.BookAbstractModel;
import com.github.fa2bio.domain.model.Book;

@Component
public class BookModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BookModel toModel(Book book) {
		return modelMapper.map(book, BookModel.class);
	}
	
	public BookAbstractModel toResumoModel(Book book) {
		return modelMapper.map(book, BookAbstractModel.class);
	}
	
	public List<BookAbstractModel> toCollectionModel(List<Book> books){
		return books.stream()
				.map(book -> toResumoModel(book))
				.collect(Collectors.toList());
	}
	
	public List<BookAbstractModel> toCollectionModel(Page<Book> books){
		return books.stream()
				.map(book -> toResumoModel(book))
				.collect(Collectors.toList());
	}
}
