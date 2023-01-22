package com.github.fa2bio.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Client {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String uuiCode;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String cpf;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "client")
	private List<Book> books = new ArrayList<>();
	
	@PrePersist
	private void generateCode() {
		setUuiCode(UUID.randomUUID().toString());
	}
	
	public void addBook(Book book) {
		getBooks().add(book);
		book.setClient(this);
	}
	
	public void removeBook(Book book) {
		getBooks().remove(book);
		book.setClient(null);
	}
}
