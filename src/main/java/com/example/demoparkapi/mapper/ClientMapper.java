package com.example.demoparkapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

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
	public static List<ClientResponseDTO> toListDto(List<Client> list) {
		return list.stream().map(x->toDto(x)).collect(Collectors.toList());
	}

}
