package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.BookInpuDisassembler;
import com.github.fa2bio.api.assembler.BookModelAssembler;
import com.github.fa2bio.api.model.BookModel;
import com.github.fa2bio.api.model.BookAbstractModel;
import com.github.fa2bio.api.model.input.BookInput;
import com.github.fa2bio.api.swaggeropenapi.BookControllerSwagger;
import com.github.fa2bio.domain.model.Book;
import com.github.fa2bio.domain.model.VolumeInfo;
import com.github.fa2bio.domain.repository.BookRepository;
import com.github.fa2bio.domain.service.IsbnService;
import com.github.fa2bio.domain.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController implements BookControllerSwagger{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private IsbnService isbnService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookModelAssembler livroModelAssembler;
	
	@Autowired
	private BookInpuDisassembler livroInpuDisassembler;
	
	@Override
	@GetMapping(path="/list")
	public List<BookAbstractModel> list(){
		List<Book> allBooks = bookRepository.findAll();
		return livroModelAssembler.toCollectionModel(allBooks);
	}
	
	@Override
	@GetMapping(path="/list/{numPages}/{numElements}")
	public List<BookAbstractModel> listByPageable(@PathVariable int numPages, @PathVariable int numElements){
		if(numPages<0) numPages=0;
		if(numElements<=0) numElements=1;
		Pageable page = PageRequest.of(numPages, numElements);
		Page<Book> allBooks = bookRepository.findAll(page);
		return livroModelAssembler.toCollectionModel(allBooks);
	}
	
	@Override
	@GetMapping(path="/find/{uuiCode}")
	public BookModel findByCode(@PathVariable String uuiCode) {
		Book book = bookService.fetchOrFail(uuiCode);
		return livroModelAssembler.toModel(book);
	}
	
	@PostMapping(path="/register")
	public BookModel register(@RequestBody @Valid BookInput bookInput) {
		Book book = livroInpuDisassembler.toDomainObject(bookInput);
		VolumeInfo volume = isbnService.findBookByIsbn(book.getIsbn());
		book.setVolumeInfo(volume);
		book = bookService.save(book);
		return livroModelAssembler.toModel(book);
	}
	
	@Override
	@PutMapping(path="/update/{uuiCode}")
	public BookModel update(
			@PathVariable String uuiCode,
			@RequestBody @Valid BookInput livroInput) {
		
		Book bookCurrent = bookService.authorizeUpdate(uuiCode);
		livroInpuDisassembler.copyToDomainObject(livroInput, bookCurrent);
		bookCurrent.setClient(null);
		VolumeInfo volume = isbnService.findBookByIsbn(bookCurrent.getIsbn());
		bookCurrent.setVolumeInfo(volume);
		bookCurrent = bookService.save(bookCurrent);
		
		return livroModelAssembler.toModel(bookCurrent);
	}
	
	@Override
	@DeleteMapping(path="/remove/{uuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String uuiCode) {
		bookService.delete(uuiCode);
		
	}
	
}
