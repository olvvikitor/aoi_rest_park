package com.example.demoparkapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.example.demoparkapi.dtos.UserCreateDTO;
import com.example.demoparkapi.dtos.UserResponseDto;
import com.example.demoparkapi.entities.User;


public class UserMapper {

	public static User toUser(UserCreateDTO userDto) {
		return new ModelMapper().map(userDto, User.class);
	}

	public static UserResponseDto toDto(User user) {
		String role = user.getRole().name().substring("ROLE_".length());
		PropertyMap<User, UserResponseDto> props = new PropertyMap<User, UserResponseDto>() {

			@Override
			protected void configure() {
				map().setRole(role);
			}
		};
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(props);
		return mapper.map(user, UserResponseDto.class);
	}
	
	
	public static List<UserResponseDto> toListDto(List<User> users) {	
		//na expressao lambda eu pego a lista !users! e seleciono cada objeto da lista !x! transormando em dto
		return users.stream().map(x-> toDto(x)).collect(Collectors.toList());
		
	}
}
