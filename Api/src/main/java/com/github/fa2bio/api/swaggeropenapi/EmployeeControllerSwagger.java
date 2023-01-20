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

@Api(tags = "Employees")
public interface EmployeeControllerSwagger {

	@ApiOperation("List all Employees")
	List<EmployeeAbstractModel> list();
	
	@ApiOperation("List all Employees by Pageable")
	List<EmployeeAbstractModel> listByPageable(
			@ApiParam(value = "Page Number", example = "1", required = true) 
			int numeroPagina,
			@ApiParam(value = "Page Elements Number", example = "3", required = true) 
			int numeroElementos);
	
	@ApiOperation("Find a Employee by code")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid Code", response = Problem.class),
		@ApiResponse(code = 404, message = "Employee Not Found", response = Problem.class)
	})
	EmployeeClientModel findByCode(
			@ApiParam(value = "UUID Employee Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("List the zip codes with the highest incidence between employees")
	public List<IncidenceModel> incidence();
	
	@ApiOperation("Register a Employee")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Employee registered"),
	})
	EmployeeModel register(
			@ApiParam(name = "Body", value = "Representation of a new Employee", required = true)
			EmployeeInput employeeInput);
	
	@ApiOperation("Update Employee By Code")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated Employee"),
		@ApiResponse(code = 404, message = "Employee Not Found", response = Problem.class)
	})
	EmployeeModel update(
			@ApiParam(value = "UUID Employee Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representation of a new Employee", required = true)
			EmployeeInput employeeInput);
	
	@ApiOperation("Remove a Employee by code")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Employee removed"),
		@ApiResponse(code = 404, message = "Employee Not Found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "UUID Employee Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
}
