package com.github.fa2bio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.swaggeropenapi.FuncionarioClienteSwagger;
import com.github.fa2bio.domain.service.EmployeeService;

@RestController
@RequestMapping("/employees/employee/{employeeUuiCode}")
public class EmployeeClientController implements FuncionarioClienteSwagger{

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	@PutMapping("/client/{clientUuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable String employeeUuiCode, @PathVariable String clientUuiCode) {
		employeeService.associateClient(employeeUuiCode, clientUuiCode);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/client/{clientUuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable String employeeUuiCode, @PathVariable String clientUuiCode) {
		employeeService.disassociateClient(employeeUuiCode, clientUuiCode);
		return ResponseEntity.noContent().build();
	}
}
