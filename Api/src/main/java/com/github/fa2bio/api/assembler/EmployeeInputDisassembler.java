package com.github.fa2bio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.input.EmployeeInput;
import com.github.fa2bio.domain.model.Employee;

@Component
public class EmployeeInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Employee toDomainObject(EmployeeInput employeeInput) {
		return modelMapper.map(employeeInput, Employee.class);
	}
	
	public void copyToDomainObject(EmployeeInput funcionarioInput, Employee employee) {
		modelMapper.map(funcionarioInput, employee);
	}
}
