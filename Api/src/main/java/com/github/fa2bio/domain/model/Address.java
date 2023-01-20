package com.github.fa2bio.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(name = "address_cep")
	private String cep;
	
	@Column(name = "address_logradouro")
	private String logradouro;
	
	@Column(name = "address_complemento")
	private String complemento;
	
	@Column(name = "address_bairro")
	private String bairro;
	
	@Column(name = "address_localidade")
	private String localidade;
}
