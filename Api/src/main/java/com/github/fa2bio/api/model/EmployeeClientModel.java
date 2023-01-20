package com.github.fa2bio.api.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeClientModel extends EmployeeModel{

	private Set<ClientAbstractModel> clients;
	
}
