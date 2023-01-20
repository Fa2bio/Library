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

@Api(tags = "Livros")
public interface LivroControllerSwagger {

	@ApiOperation("Lista Todos Os Livros")
	List<BookAbstractModel> list();
	
	@ApiOperation("Lista Os Livros Por Paginação")
	List<BookAbstractModel> listByPageable(
			@ApiParam(value = "Número Da Página", example = "1", required = true) 
			int numeroPagina,
			@ApiParam(value = "Número De Elementos A Ser Exibido Na Página", example = "2", required = true) 
			int numeroElementos);
	
	@ApiOperation("Procura Um Livro Por Código")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Livro Não Encontrado", response = Problem.class)
	})
	BookModel findByCode(
			@ApiParam(value = "Código UUID Do Livro", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("Registra Um Livro")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Livro registrado"),
	})
	BookModel register(
			@ApiParam(name = "Body", value = "Representação de um novo Livro", required = true)
			BookInput livroInput);
	
	@ApiOperation("Atualiza Um Livro Por Código")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Livro Atualizado"),
		@ApiResponse(code = 404, message = "Livro Não Encontrado", response = Problem.class)
	})
	BookModel update(
			@ApiParam(value = "Código UUID Do Livro", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representação de um Livro com novos dados", required = true)
			BookInput livroInput);
	
	@ApiOperation("Remove um Livro por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Livro Deletado"),
		@ApiResponse(code = 404, message = "Livro Não Encontrado", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "Código UUID Do Livro", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true)
			String uuiCode);

}
