package com.example.demoparkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoparkapi.entities.Vaga;
import com.example.demoparkapi.exceptions.CodigoUniqueVioleationException;
import com.example.demoparkapi.exceptions.UserNotFoundException;
import com.example.demoparkapi.repositories.VagaRepositoriy;

@Service
public class VagaService {

	@Autowired
	VagaRepositoriy vagarepository;
	
	@Transactional
	public Vaga create(Vaga vaga) {
		try {
			return vagarepository.save(vaga);
		}catch(DataIntegrityViolationException ex) {
			throw new CodigoUniqueVioleationException(String.format("Vaga com codigo {%s}", vaga.getCodigo()));
		}
	}
	
	@Transactional(readOnly = true)
	public Vaga findByCodigo(String codigo) {
		return vagarepository.findByCodigo(codigo).orElseThrow(()->new UserNotFoundException(String.format("vaga com codigo {%s} não encontrada", codigo)));
	}
}
