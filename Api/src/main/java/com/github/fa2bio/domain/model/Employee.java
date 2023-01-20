package com.github.fa2bio.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Employee {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String uuiCode;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String cep;
	
	@Embedded
	private Address address;
	
	@ManyToMany
	@JoinTable(name = "employee_client", joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Set<Client> clients = new HashSet<>();
	
	@PrePersist
	private void generateCode() {
		setUuiCode(UUID.randomUUID().toString());
	}
	
	public boolean addClient(Client client) {
		return getClients().add(client);
	}
	
	public boolean removeClient(Client client) {
		return getClients().remove(client);
	}
}
