package com.github.fa2bio.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Book {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String isbn;
	
	@Column(nullable = false)
	private String uuiCode;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Embedded
	private VolumeInfo volumeInfo;
	
	@PrePersist
	private void generateCode() {
		setUuiCode(UUID.randomUUID().toString());
	}
}
