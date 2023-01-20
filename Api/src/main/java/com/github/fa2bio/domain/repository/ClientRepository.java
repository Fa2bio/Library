package com.github.fa2bio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.fa2bio.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query("from Client where uui_code = :uui_code")	
	Optional<Client> findByCode(String uui_code);
	
}
