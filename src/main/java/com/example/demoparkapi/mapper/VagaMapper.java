package com.example.demoparkapi.mapper;

import org.modelmapper.ModelMapper;

import com.example.demoparkapi.dtos.VagaCreateDto;
import com.example.demoparkapi.dtos.VagaResponseDto;
import com.example.demoparkapi.entities.Vaga;

public class VagaMapper {

	private VagaMapper() {
		super();
	}
	
	public static Vaga toVaga(VagaCreateDto vagaDto) {
	return new ModelMapper().map(vagaDto, Vaga.class); 
	}
	
	public static VagaResponseDto toDto(Vaga vaga) {
		return new ModelMapper().map(vaga, VagaResponseDto.class);
	}
}
