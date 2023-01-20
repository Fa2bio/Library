package com.github.fa2bio.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fa2bio.domain.exception.BookNotFoundException;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.model.Book;
import com.github.fa2bio.domain.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	private static final String book_EM_USO 
	= "The book of code %s can't be %s, because it's in use.";
	
	@Transactional
	public Book save(Book book) {	
		return bookRepository.save(book);
	}
	
	@Transactional
	public void delete(String uuiCode) {
		Book book = fetchOrFail(uuiCode);
		if(book.getClient()!=null) {
			throw new EntityInUseException(String.format(book_EM_USO, uuiCode, "removed"));
		}else {
			bookRepository.delete(book);
			bookRepository.flush();
		}
	}
	
	public Book fetchOrFail(String uuiCode) {
		return bookRepository.findByCode(uuiCode)
				.orElseThrow(() -> new BookNotFoundException(uuiCode));
	}
	
	public Book authorizeUpdate(String uuiCode) {
		Book book = fetchOrFail(uuiCode);
		if(book.getClient()!=null) {
			throw new EntityInUseException(String.format(book_EM_USO, uuiCode, "updated"));
		}
		return book;
	}
}
