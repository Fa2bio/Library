package com.github.fa2bio.api.swaggeropenapi;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Funcionarios - Clientes")
public interface FuncionarioClienteSwagger {

	@ApiOperation("Adiciona Um Funcionario Ao Funcionario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Funcionario Adicionado", response = Problem.class),
		@ApiResponse(code = 404, message = "Funcionario/Cliente Não Encontrado", response = Problem.class)
	})
	ResponseEntity<Void> associate(			
			@ApiParam(value = "Código UUID Do Funcionario", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String funcionarioUuiCode, 			
			@ApiParam(value = "Código UUID Do Cliente", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String clienteUuiCode);
	
	@ApiOperation("Remove Um Funcionario Do Funcionario")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente Removido", response = Problem.class),
		@ApiResponse(code = 404, message = "Funcionario Não Encontrado", response = Problem.class),
		@ApiResponse(code = 404, message = "Cliente Não Encontrado", response = Problem.class)
	})
	ResponseEntity<Void> disassociate(			
			@ApiParam(value = "Código UUID Do Funcionario", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String funcionarioUuiCode, 			
			@ApiParam(value = "Código UUID Do Cliente", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String clienteUuiCode);
}
