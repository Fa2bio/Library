package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.AddressInputModel;
import com.github.fa2bio.domain.model.Address;

@Component
public class AddressModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Address toDomainObject(AddressInputModel addressInputModel) {
		return modelMapper.map(addressInputModel, Address.class);
	}
}
