package com.example.demoparkapi.util;

import java.time.LocalDateTime;

public class EstacionamentoUtils {

	// 2023-03-28T15: 23:48
	public static String gerarRecibo() {
		LocalDateTime date = LocalDateTime.now();
		String recibo = date.toString().substring(0, 19);
		return recibo.replace("-", "").replace(":", "").replace("T", "-");

	}

}
