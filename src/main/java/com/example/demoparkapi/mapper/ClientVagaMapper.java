package com.example.demoparkapi.mapper;

import org.modelmapper.ModelMapper;

import com.example.demoparkapi.dtos.EstacionamentoCreateeDto;
import com.example.demoparkapi.dtos.EstacionamentoResponseDto;
import com.example.demoparkapi.entities.ClientVaga;

public class ClientVagaMapper {
	
	public ClientVagaMapper() {
		
	}
	
	public static ClientVaga toClientVaga(EstacionamentoCreateeDto dto) {
		return new ModelMapper().map(dto, ClientVaga.class);
	}
	public static EstacionamentoResponseDto toDto(ClientVaga clientVaga) {
		return new ModelMapper().map(clientVaga, EstacionamentoResponseDto.class);
	}

}
