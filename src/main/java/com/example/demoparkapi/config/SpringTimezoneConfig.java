package com.example.demoparkapi.config;


import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SpringTimezoneConfig {
	
	@PostConstruct // Essa anotcao faz com que apos a execuxao do metodo construtor, o proximo sera o connfig
	public void timezoneConfig() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
