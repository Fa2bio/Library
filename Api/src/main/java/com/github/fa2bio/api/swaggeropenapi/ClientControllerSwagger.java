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

@Api(tags = "Clients")
public interface ClientControllerSwagger {

	@ApiOperation("List all Clients")
	List<ClientAbstractModel> list();
	
	@ApiOperation("List all Clients by Pageable")
	List<ClientAbstractModel> listByPageable(
			@ApiParam(value = "Page Number", example = "1", required = true) 
			int numPages,
			@ApiParam(value = "Page Elements Number", example = "2", required = true) 
			int numElements);
	
	@ApiOperation("Find a Client by code")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid Code", response = Problem.class),
		@ApiResponse(code = 404, message = "Client Not Found", response = Problem.class)
	})
	ClientBookModel findByCode(
			@ApiParam(value = "UUID Client Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
	
	@ApiOperation("Register a Client")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Client registered"),
	})
	ClientModel register(
			@ApiParam(name = "Body", value = "Representation of a new Client", required = true)
			ClientInput clientInput);
	
	@ApiOperation("Update Client By Code")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated Client"),
		@ApiResponse(code = 404, message = "Client Not Found", response = Problem.class)
	})
	ClientModel update(
			@ApiParam(value = "UUID Client code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode,
			@ApiParam(name = "Body", value = "Representation of a new client", required = true)
			ClientInput clientInput);
	
	@ApiOperation("Remove a Client by code")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Client removed"),
		@ApiResponse(code = 404, message = "Client Not Found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "UUID Client code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String uuiCode);
}
