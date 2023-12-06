package com.example.demoparkapi.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoparkapi.dtos.EstacionamentoResponseDto;
import com.example.demoparkapi.entities.ClientVaga;
import com.example.demoparkapi.entities.Vaga;
import com.example.demoparkapi.enuns.StatusVaga;
import com.example.demoparkapi.util.EstacionamentoUtils;

@Service
public class EstacionamentoService {
	
	@Autowired
	private ClientVagaService clientVagaService;
	
	@Autowired
	private VagaService vagaService;
	
	public ClientVaga checkIn(ClientVaga clientVaga) {
		Vaga vaga = vagaService.findByVagaLivre();
		vaga.setStatus(StatusVaga.OCUPADA);
		clientVaga.setVagas(vaga);
		clientVaga.setDataEntrada(LocalDateTime.now());
		clientVaga.setRecibo(EstacionamentoUtils.gerarRecibo());
		return clientVagaService.create(clientVaga);
		
	}
}
