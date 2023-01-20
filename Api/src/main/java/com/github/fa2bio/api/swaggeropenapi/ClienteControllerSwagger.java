package com.github.fa2bio.api.swaggeropenapi;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.ClientBookModel;
import com.github.fa2bio.api.model.ClientModel;
import com.github.fa2bio.api.model.ClientAbstractModel;
import com.github.fa2bio.api.model.input.ClientInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clientes")
public interface ClienteControllerSwagger {

	@ApiOperation("Lista Todos Os Clientes")
	List<ClientAbstractModel> list();
	
	@ApiOperation("Lista Os Clientes Por Paginação")
	List<ClientAbstractModel> listByPageable(
			@ApiParam(value = "Número Da Página", example = "1", required = true) 
			int numeroPagina,
			@ApiParam(value = "Número De Elementos A Ser Exibido Na Página", example = "2", required = true) 
			int numeroElementos);
	
	@ApiOperation("Procura Um Cliente Por Código")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cliente Não Encontrado", response = Problem.class)
	})
	ClientBookModel findByCode(
			@ApiParam(value = "Código UUID Do Cliente", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("Registra Um Cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente registrado"),
	})
	ClientModel register(
			@ApiParam(name = "Body", value = "Representação de um novo cliente", required = true)
			ClientInput clienteInput);
	
	@ApiOperation("Atualiza Um Cliente Por Código")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente Atualizado"),
		@ApiResponse(code = 404, message = "Cliente Não Encontrado", response = Problem.class)
	})
	ClientModel update(
			@ApiParam(value = "Código UUID Do Cliente", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representação de um cliente com novos dados", required = true)
			ClientInput clienteInput);
	
	@ApiOperation("Remove um cliente por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente Deletado"),
		@ApiResponse(code = 404, message = "Cliente Não Encontrado", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "Código UUID Do Cliente", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
}
