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
import com.github.fa2bio.api.assembler.EmployeeInputDisassembler;
import com.github.fa2bio.api.assembler.EmployeeModelAssembler;
import com.github.fa2bio.api.model.EmployeeClientModel;
import com.github.fa2bio.api.model.EmployeeModel;
import com.github.fa2bio.api.model.EmployeeAbstractModel;
import com.github.fa2bio.api.model.IncidenceModel;
import com.github.fa2bio.api.model.input.AddressInputModel;
import com.github.fa2bio.api.model.input.EmployeeInput;
import com.github.fa2bio.api.swaggeropenapi.FuncionarioControllerSwagger;
import com.github.fa2bio.domain.model.Address;
import com.github.fa2bio.domain.model.Employee;
import com.github.fa2bio.domain.repository.EmployeeRepository;
import com.github.fa2bio.domain.service.CepService;
import com.github.fa2bio.domain.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController implements FuncionarioControllerSwagger{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeInputDisassembler employeeInputDisassembler;
	
	@Autowired
	private EmployeeModelAssembler employeeModelAssembler;
	
	@Autowired
	private AddressModelAssembler addressModelAssembler;
	
	@Autowired
	private CepService cepService;
	
	@Override
	@GetMapping(path="/list")
	public List<EmployeeAbstractModel> list(){
		List<Employee> allEmployees = employeeRepository.findAll();
		return employeeModelAssembler.toCollectionModel(allEmployees);
	}
	
	@Override
	@GetMapping(path="/list/{numPages}/{numElements}")
	public List<EmployeeAbstractModel> listByPageable(@PathVariable int numPages, @PathVariable int numElements){
		if(numPages<0) numPages=0;
		if(numElements<=0) numElements=1;
		Pageable page = PageRequest.of(numPages, numElements);
		Page<Employee> allEmployees = employeeRepository.findAll(page);
		return employeeModelAssembler.toCollectionPageModel(allEmployees);
	}
	
	@Override
	@GetMapping(path="/find/{uuiCode}")
	public EmployeeClientModel findByCode(@PathVariable String uuiCode) {
		Employee employee = employeeService.fetchOrFail(uuiCode);
		return employeeModelAssembler.toClienteModel(employee);
	}
	
	@Override
	@GetMapping(path="/list/cepincidence")
	public List<IncidenceModel> incidence(){	
		return employeeService.findIncidences();
	}
	
	@Override
	@PostMapping(path="/register")
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeModel register(@RequestBody @Valid EmployeeInput employeeInput) {
		Employee employee = employeeInputDisassembler.toDomainObject(employeeInput);
		AddressInputModel addressInput = cepService.findAddress(employee.getCep());
		Address address = addressModelAssembler.toDomainObject(addressInput);

		employee.setAddress(address);		
		employee = employeeService.save(employee);
		return employeeModelAssembler.toModel(employee);
	}
		
	@Override
	@PutMapping(path="/update/{uuiCode}")
	public EmployeeModel update(
			@PathVariable String uuiCode,
			@RequestBody @Valid EmployeeInput funcionarioInput) {
		
		Employee employeeCurrent = employeeService.fetchOrFail(uuiCode);
		employeeInputDisassembler.copyToDomainObject(funcionarioInput, employeeCurrent);
		AddressInputModel addressInput = cepService.findAddress(employeeCurrent.getCep());
		Address address = addressModelAssembler.toDomainObject(addressInput);
		
		employeeCurrent.setAddress(address);		
		employeeCurrent = employeeService.save(employeeCurrent);;		
		return employeeModelAssembler.toModel(employeeCurrent);
	}
	
	@Override
	@DeleteMapping(path="/remove/{uuiCode}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String uuiCode) {
		employeeService.delete(uuiCode);
		
	}
	
}
