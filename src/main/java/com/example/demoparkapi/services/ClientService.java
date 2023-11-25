package com.example.demoparkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.entities.Client;
import com.example.demoparkapi.exceptions.CpfUniqueVioleationException;
import com.example.demoparkapi.exceptions.UserNotFoundException;
import com.example.demoparkapi.repositories.ClientRepository;
import com.example.demoparkapi.repositories.projection.ClientProjection;


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
	public Page<ClientProjection>findAll(Pageable pageable){
		return clientRepository.findAllpage(pageable);
			
	}
}
