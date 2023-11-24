package com.example.demoparkapi.services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.exceptions.CpfUniqueVioleationException;
import com.example.demoparkapi.exceptions.UserNotFoundException;
import com.example.demoparkapi.repositories.ClientRepository;


@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	
	@Transactional
	public Client create(Client client) {
		try {
			return clientRepository.save(client);
		} catch (DataIntegrityViolationException ex) {
			throw new CpfUniqueVioleationException(String.format("CPF '%s' já existe no sistema,", client.getCpf()));
		}
	}
	
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Cliente não encontrado"));

	}
	
	@Transactional(readOnly = true)
	public List<Client>findAll(){
		List<Client> list = clientRepository.findAll();
		return list;
		
		
	}
}
