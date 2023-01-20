package com.github.fa2bio.api.swaggeropenapi;

import org.springframework.http.ResponseEntity;

import com.github.fa2bio.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Employees - Clients")
public interface EmployeeClientSwagger {

	@ApiOperation("Associate a Client to the Employee")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associated Client", response = Problem.class),
		@ApiResponse(code = 404, message = "Employee/Client Not Found", response = Problem.class)
	})
	ResponseEntity<Void> associate(			
			@ApiParam(value = "UUID Employee Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String employeeUuiCode, 			
			@ApiParam(value = "UUID Client Code", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String clientUuiCode);
	
	@ApiOperation("Disassociate a Client to the Employee")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Disassociate Client", response = Problem.class),
		@ApiResponse(code = 404, message = "Employee/Client Not Found", response = Problem.class)
	})
	ResponseEntity<Void> disassociate(			
			@ApiParam(value = "UUID Employee Code", example = "aa71487f-61e6-4fea-b631-5c12848e1b7a", required = true) 
			String employeeUuiCode, 			
			@ApiParam(value = "UUID Client Code", example = "e5912001-6fed-4b8f-bcb3-bce4d59ce95e", required = true) 
			String clientUuiCode);
}
