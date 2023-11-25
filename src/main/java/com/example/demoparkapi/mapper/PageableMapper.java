package com.example.demoparkapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.example.demoparkapi.dtos.PageableDto;

public class PageableMapper {


	private PageableMapper() {
		super();
	}
	
	
	public static PageableDto toDto(Page<?> page) {
		return new ModelMapper().map(page, PageableDto.class);
	}
	
}
