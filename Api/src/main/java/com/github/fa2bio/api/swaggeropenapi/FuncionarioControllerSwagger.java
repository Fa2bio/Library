package com.github.fa2bio.api.swaggeropenapi;

import java.util.List;

import com.github.fa2bio.api.exceptionhandler.Problem;
import com.github.fa2bio.api.model.EmployeeClientModel;
import com.github.fa2bio.api.model.EmployeeModel;
import com.github.fa2bio.api.model.EmployeeAbstractModel;
import com.github.fa2bio.api.model.IncidenceModel;
import com.github.fa2bio.api.model.input.EmployeeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Funcionarios")
public interface FuncionarioControllerSwagger {

	@ApiOperation("Lista Todos Os Funcionários")
	List<EmployeeAbstractModel> list();
	
	@ApiOperation("Lista Os Funcionarios Por Paginação")
	List<EmployeeAbstractModel> listByPageable(
			@ApiParam(value = "Número Da Página", example = "1", required = true) 
			int numeroPagina,
			@ApiParam(value = "Número De Elementos A Ser Exibido Na Página", example = "3", required = true) 
			int numeroElementos);
	
	@ApiOperation("Procura Um Funcionário Por Código")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Funcionário Não Encontrado", response = Problem.class)
	})
	EmployeeClientModel findByCode(
			@ApiParam(value = "Código UUID Do Funcionário", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("Lista os CEP's Com Maior Incidência Entre Os Funcionários")
	public List<IncidenceModel> incidence();
	
	@ApiOperation("Registra Um Funcionário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Funcionário registrado"),
	})
	EmployeeModel register(
			@ApiParam(name = "Body", value = "Representação de um novo Funcionário", required = true)
			EmployeeInput FuncionárioInput);
	
	@ApiOperation("Atualiza Um Funcionário Por Código")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Funcionário Atualizado"),
		@ApiResponse(code = 404, message = "Funcionário Não Encontrado", response = Problem.class)
	})
	EmployeeModel update(
			@ApiParam(value = "Código UUID Do Funcionário", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representação de um Funcionário com novos dados", required = true)
			EmployeeInput FuncionárioInput);
	
	@ApiOperation("Remove um Funcionário por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Funcionário Deletado"),
		@ApiResponse(code = 404, message = "Funcionário Não Encontrado", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "Código UUID Do Funcionário", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
}
