package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.ClientBookModel;
import com.github.fa2bio.api.model.ClientModel;
import com.github.fa2bio.api.model.ClientAbstractModel;
import com.github.fa2bio.domain.model.Client;

@Component
public class ClientModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClientModel toModel(Client client) {
		return modelMapper.map(client, ClientModel.class);
	}
	
	public ClientAbstractModel toResumoModel(Client client) {
		return modelMapper.map(client, ClientAbstractModel.class);
	}
	
	public ClientBookModel toClientBookModel(Client client) {
		return modelMapper.map(client, ClientBookModel.class);
	}
	
	public List<ClientAbstractModel> toCollectionModel(List<Client> clients){
		return clients.stream()
				.map(client -> toResumoModel(client))
				.collect(Collectors.toList());
	}
	
	public List<ClientAbstractModel> toCollectionModel(Page<Client> clients){
		return clients.stream()
				.map(client -> toResumoModel(client))
				.collect(Collectors.toList());
	}
}
