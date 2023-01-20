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

import com.github.fa2bio.api.swaggeropenapi.ClienteLivroControllerSwagger;
import com.github.fa2bio.domain.service.ClientService;

@RestController
@RequestMapping("clients/client/{clientUuiCode}")
public class ClientBookController implements ClienteLivroControllerSwagger{

	@Autowired
	private ClientService clientService;
	
	@Override
	@PutMapping("/book/{bookUuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> addBook(@PathVariable String clientUuiCode, @PathVariable String bookUuiCode){
		clientService.associateBook(clientUuiCode, bookUuiCode);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/book/{bookUuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> removeBook(@PathVariable String clientUuiCode, @PathVariable String bookUuiCode){
		clientService.disassociateBook(clientUuiCode, bookUuiCode);
		return ResponseEntity.noContent().build();
	}
}
