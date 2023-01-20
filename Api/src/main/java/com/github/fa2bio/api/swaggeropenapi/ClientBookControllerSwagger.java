package com.github.fa2bio.api.swaggeropenapi;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Clients - Books")
public interface ClientBookControllerSwagger {

	@ApiOperation("Associate a Book to the Client")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associated Book", response = Problem.class),
		@ApiResponse(code = 404, message = "Client/Book Not Found", response = Problem.class)
	})
	ResponseEntity<Void> addBook(			
			@ApiParam(value = "UUID Client Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String clientUuiCode, 			
			@ApiParam(value = "UUID Book Code", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String bookUuiCode);
	
	@ApiOperation("Disassociate a Book to the Client")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Disassociate Book", response = Problem.class),
		@ApiResponse(code = 404, message = "Client/Book Not Found", response = Problem.class),
	})
	ResponseEntity<Void> removeBook(			
			@ApiParam(value = "UUID Client Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String clientUuiCode, 			
			@ApiParam(value = "UUID Book Code", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String bookUuiCode);
}
