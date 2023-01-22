package com.github.fa2bio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.github.fa2bio.api.model.EmployeeClientModel;
import com.github.fa2bio.api.model.EmployeeModel;
import com.github.fa2bio.api.model.EmployeeAbstractModel;
import com.github.fa2bio.domain.model.Employee;

@Component
public class EmployeeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EmployeeModel toModel(Employee employee) {
		return modelMapper.map(employee, EmployeeModel.class);
	}
	
	public EmployeeAbstractModel toResumoModel(Employee employee) {
		return modelMapper.map(employee, EmployeeAbstractModel.class);
	}
	
	public EmployeeClientModel toClienteModel(Employee employee) {
		return modelMapper.map(employee, EmployeeClientModel.class);
	}
		
	public List<EmployeeAbstractModel> toCollectionModel(List<Employee> employees){
		return employees.stream()
				.map(employee -> toResumoModel(employee))
				.collect(Collectors.toList());
	}
	
	public List<EmployeeAbstractModel> toCollectionModel(Page<Employee> employees){
		return employees.stream()
				.map(funcionario -> toResumoModel(funcionario))
				.collect(Collectors.toList());
	}
}
