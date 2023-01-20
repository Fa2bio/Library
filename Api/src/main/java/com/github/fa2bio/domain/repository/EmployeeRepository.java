package com.github.fa2bio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query("from Employee where uui_code = :uui_code")	
	Optional<Employee> findByCode(String uui_code);
}
