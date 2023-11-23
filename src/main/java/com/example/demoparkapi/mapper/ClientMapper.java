package com.example.demoparkapi.mapper;

import org.modelmapper.ModelMapper;

import com.example.demoparkapi.dtos.ClientCreateDTO;
import com.example.demoparkapi.dtos.ClientResponseDTO;
import com.example.demoparkapi.entities.Client;

public class ClientMapper {
	
	public static Client toCient(ClientCreateDTO dto) {
		return new ModelMapper().map(dto, Client.class);
	}
	public static ClientResponseDTO toDto(Client client) {
		return new ModelMapper().map(client, ClientResponseDTO.class);
	}

}
