package com.github.fa2bio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fa2bio.domain.exception.ClientNotFoundException;
import com.github.fa2bio.domain.exception.EntityInUseException;
import com.github.fa2bio.domain.model.Client;
import com.github.fa2bio.domain.model.Book;
import com.github.fa2bio.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clienteRepository;
	
	@Autowired
	private BookService livroService;
	
	private static final String CPF_IN_USE 
	= "The customer with CPF %s cannot be saved, because there is already a record for this CPF.";
	
	private static final String CLIENT_IN_USE 
	= "The customer with CPF %s cannot be removed, because it has associated books.";

	@Transactional
	public Client save(Client cliente) {
		Client clientCurrent = null;
		try {
			clientCurrent = clienteRepository.save(cliente);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(CPF_IN_USE, cliente.getCpf()));
		}
		return clientCurrent;
	}
	
	@Transactional
	public void delete(String uuiCode) {
		Client client = fetchOrFail(uuiCode);
		try {
			clienteRepository.delete(client);
			clienteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(CLIENT_IN_USE, uuiCode));
		}
	}
	
	@Transactional
	public void associateBook(String clienteUuiCode, String livroUuiCode) {
		Book book = livroService.fetchOrFail(livroUuiCode);
		Client client = fetchOrFail(clienteUuiCode);
		client.addBook(book);
	}
	
	@Transactional
	public void disassociateBook(String clienteUuiCode, String livroUuiCode) {
		Book book = livroService.fetchOrFail(livroUuiCode);
		Client client = fetchOrFail(clienteUuiCode);
		client.removeBook(book);
	}
	
	public Client fetchOrFail(String uuiCode) {
		return clienteRepository.findByCode(uuiCode)
				.orElseThrow(() -> new ClientNotFoundException(uuiCode));
	}
	
}
