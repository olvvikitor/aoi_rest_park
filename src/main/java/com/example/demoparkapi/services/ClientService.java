package com.example.demoparkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.exceptions.CpfUniqueVioleationException;
import com.example.demoparkapi.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	
	public Client create(Client client) {
		try {
			return clientRepository.save(client);
		} catch (DataIntegrityViolationException ex) {
			throw new CpfUniqueVioleationException(String.format("CPF '%s' já existe no sistema,", client.getCpf()));
		}
	}
}
