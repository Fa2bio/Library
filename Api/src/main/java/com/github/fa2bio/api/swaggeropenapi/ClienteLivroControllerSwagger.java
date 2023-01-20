package com.github.fa2bio.api.swaggeropenapi;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clientes - Livros")
public interface ClienteLivroControllerSwagger {

	@ApiOperation("Adiciona Um Livro Ao Cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Livro Adicionado", response = Problem.class),
		@ApiResponse(code = 404, message = "Cliente/Livro Não Encontrado", response = Problem.class)
	})
	ResponseEntity<Void> addBook(			
			@ApiParam(value = "Código UUID Do Cliente", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String clienteUuiCode, 			
			@ApiParam(value = "Código UUID Do Livro", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String livroUuiCode);
	
	@ApiOperation("Remove Um Livro Do Cliente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Livro Removido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cliente/Livro Não Encontrado", response = Problem.class),
	})
	ResponseEntity<Void> removeBook(			
			@ApiParam(value = "Código UUID Do Cliente", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String clienteUuiCode, 			
			@ApiParam(value = "Código UUID Do Livro", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String livroUuiCode);
}
