package com.github.fa2bio.api.swaggeropenapi;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.BookModel;
import com.github.fa2bio.api.model.BookAbstractModel;
import com.github.fa2bio.api.model.input.BookInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Books")
public interface BookControllerSwagger {

	@ApiOperation("List all Books")
	List<BookAbstractModel> list();
	
	@ApiOperation("List all Books by Pageable")
	List<BookAbstractModel> listByPageable(
			@ApiParam(value = "Page Number", example = "1", required = true) 
			int numeroPagina,
			@ApiParam(value = "Page Elements Number", example = "2", required = true) 
			int numeroElementos);
	
	@ApiOperation("Find a Book by code")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid Code", response = Problem.class),
		@ApiResponse(code = 404, message = "Book Not Found", response = Problem.class)
	})
	BookModel findByCode(
			@ApiParam(value = "UUID Book Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("Register a Book")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Book registered"),
	})
	BookModel register(
			@ApiParam(name = "Body", value = "Representation of a new Book", required = true)
			BookInput bookInput);
	
	@ApiOperation("Register a Client")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated Client"),
		@ApiResponse(code = 404, message = "Book Not Found", response = Problem.class)
	})
	BookModel update(
			@ApiParam(value = "UUID Book Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representation of a new Book", required = true)
			BookInput bookInput);
	
	@ApiOperation("Remove um Livro por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Book removed"),
		@ApiResponse(code = 404, message = "Book Not Found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "UUID Book Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true)
			String uuiCode);

}
