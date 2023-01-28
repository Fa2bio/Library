package com.github.fa2bio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fa2bio.api.assembler.AddressModelAssembler;
import com.github.fa2bio.api.assembler.ClientInputDisassembler;
import com.github.fa2bio.api.assembler.ClientModelAssembler;
import com.github.fa2bio.api.model.ClientAbstractModel;
import com.github.fa2bio.api.model.ClientBookModel;
import com.github.fa2bio.api.model.ClientModel;
import com.github.fa2bio.api.model.input.AddressInputModel;
import com.github.fa2bio.api.model.input.ClientInput;
import com.github.fa2bio.api.swaggeropenapi.ClientControllerSwagger;
import com.github.fa2bio.domain.model.Address;
import com.github.fa2bio.domain.model.Client;
import com.github.fa2bio.domain.repository.ClientRepository;
import com.github.fa2bio.domain.service.CepService;
import com.github.fa2bio.domain.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController implements ClientControllerSwagger{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientInputDisassembler clientInputDisassembler;
	
	@Autowired
	private ClientModelAssembler clientModelAssembler;
	
	@Autowired
	private AddressModelAssembler addressModelAssembler;
	
	@Autowired
	private CepService cepService;
	
	@Override
	@GetMapping(path="/list")
	public List<ClientAbstractModel> list(){
		List<Client> allClients = clientRepository.findAll();
		return clientModelAssembler.toCollectionModel(allClients);
	}
	
	@Override
	@GetMapping(path="/list/{numPages}/{numElements}")
	public List<ClientAbstractModel> listByPageable(@PathVariable int numPages, @PathVariable int numElements){
		if(numPages<0) numPages=0;
		if(numElements<=0) numElements=1;
		Pageable page = PageRequest.of(numPages, numElements);
		Page<Client> allClients = clientRepository.findAll(page);
		return clientModelAssembler.toCollectionModel(allClients);
	}
	
	@Override
	@GetMapping(path="/find/{uuiCode}")
	public ClientBookModel findByCode(@PathVariable String uuiCode) {
		Client client = clientService.fetchOrFail(uuiCode);
		return clientModelAssembler.toClientBookModel(client);
	}
	
	@Override
	@PostMapping(path="/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModel register(@RequestBody @Valid ClientInput clienteInput) {
		Client client = clientInputDisassembler.toDomainObject(clienteInput);
		AddressInputModel addressInput = cepService.findAddress(client.getCep());
		Address address = addressModelAssembler.toModel(addressInput);
		
		client.setAddress(address);
		client = clientService.save(client);		
		return clientModelAssembler.toModel(client);
	}
	
	@Override
	@PutMapping(path="/update/{uuiCode}")
	public ClientModel update(
			@PathVariable String uuiCode,
			@RequestBody @Valid ClientInput clientInput) {
		
		Client clientCurrent = clientService.fetchOrFail(uuiCode);
		clientInputDisassembler.copyToDomainObject(clientInput, clientCurrent);
		AddressInputModel addressInput = cepService.findAddress(clientCurrent.getCep());;
		Address address = addressModelAssembler.toModel(addressInput);
		
		clientCurrent.setAddress(address);
		clientCurrent = clientService.save(clientCurrent);
		
		return clientModelAssembler.toModel(clientCurrent);
	}
	
	@Override
	@DeleteMapping(path="/remove/{uuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String uuiCode) {
		clientService.delete(uuiCode);
		
	}
}
