package com.example.demoparkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.entities.ClientVaga;
import com.example.demoparkapi.repositories.ClientVagaRepository;

@Service
public class ClientVagaService {
	
	@Autowired
	ClientVagaRepository repository;
	
	
	@Transactional
	public ClientVaga create(ClientVaga clientVaga) {
		return repository.save(clientVaga);
	}

}
