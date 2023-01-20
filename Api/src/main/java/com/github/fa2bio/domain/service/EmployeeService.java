package com.github.fa2bio.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.api.model.IncidenceModel;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.exception.EmployeeNotFoundException;
import com.github.fa2bio.domain.model.Client;
import com.github.fa2bio.domain.model.Employee;
import com.github.fa2bio.domain.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ClientService clientService;
	
	private static final String CPF_IN_USE 
	= "The customer with CPF %s cannot be saved, because there is already a record for this CPF.";
	
	@Transactional
	public Employee save(Employee employee) {
		Employee employeeCurrent = null;
		try {
			employeeCurrent = employeeRepository.save(employee);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(CPF_IN_USE, employee.getCpf()));
		}
		return employeeCurrent;
	}
	
	@Transactional
	public void delete(String uuiCode) {
		Employee employee = fetchOrFail(uuiCode);
		employeeRepository.delete(employee);
		employeeRepository.flush();
	}
	
	@Transactional
	public void associateClient(String employeeUuiCode, String clientUuiCode) {
		Employee employee = fetchOrFail(employeeUuiCode);
		Client client = clientService.fetchOrFail(clientUuiCode);
		employee.addClient(client);		
	}
	
	@Transactional
	public void disassociateClient(String employeeUuiCode, String clientUuiCode) {
		Employee employee = fetchOrFail(employeeUuiCode);
		Client client = clientService.fetchOrFail(clientUuiCode);
		employee.removeClient(client);		
	}
	
	public Employee fetchOrFail(String uuiCode) {
		return employeeRepository.findByCode(uuiCode)
				.orElseThrow(() -> new EmployeeNotFoundException(uuiCode));
	}
	
	public List<IncidenceModel> findIncidences(){
		List<IncidenceModel> incidences = new ArrayList<>();
		List<Employee> allemployees = employeeRepository.findAll();
		Employee[] array_employees = allemployees.toArray(new Employee[0]);
		
		for (int i = 0; i < array_employees.length; i++) {
			int qtdEmpCep = 0;
			IncidenceModel incidence = new IncidenceModel();
			for (int j = i; j < array_employees.length; j++) {
				if(array_employees[i].getCep().equals(array_employees[j].getCep())) {
					i=j;
					qtdEmpCep++;
					incidence.addList(array_employees[j].getName());
				}
			}
			incidence.setQtdEmployees(qtdEmpCep);
			incidence.setCep(array_employees[i].getCep());
			incidences.add(incidence);
		}

		incidences.sort((o1, o2) -> o1.compareTo(o2));
		return incidences;
	}
}
