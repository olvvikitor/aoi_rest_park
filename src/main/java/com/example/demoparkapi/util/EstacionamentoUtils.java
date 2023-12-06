package com.example.demoparkapi.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EstacionamentoUtils {
	
	private static final double PRIMEIRO_15_MINUTOS = 5.00;
	private static final double PRIMEIRO_60_MINUTOS = 9.25;
	private static final double ADICIONAL_15_MINUTOS = 1.75;
	 private static final double DESCONTO_PERCENTUAL = 0.30;
	private static final BigDecimal desconto = null;

	private EstacionamentoUtils() {
		
	}
	
	public static BigDecimal calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
		  entrada = LocalDateTime.parse("2023-03-01T13:00:00");
		   saida = LocalDateTime.parse("2023-03-01T13:15:00");
		long minutes = entrada.until(saida, ChronoUnit.MINUTES);//until calcula a diferença de tempo entre dois parametros
		double total = 0.0;
		
		if(minutes <= 15) {
			 total = PRIMEIRO_15_MINUTOS;
		}
		else if (minutes <=60) {
			total = PRIMEIRO_60_MINUTOS;
		}
		else {
			total = PRIMEIRO_60_MINUTOS;
			long minutosExcedente = minutes - 60;
			long intervalosDe15minutos = minutosExcedente/15;
			total = PRIMEIRO_60_MINUTOS + intervalosDe15minutos*ADICIONAL_15_MINUTOS;
			}
		//RoundingMode.HALF_EVEN. Isso significa que o número será arredondado para o dígito mais próximo
		return new BigDecimal(total).setScale(2,RoundingMode.HALF_EVEN);
		
	}
	    
	    public static BigDecimal calcularDesconto(BigDecimal custo, long numeroDeVezes) {
	    	if((numeroDeVezes >0) && (numeroDeVezes%10 == 0)) {
	    		desconto = desconto.multiply(new BigDecimal(DESCONTO_PERCENTUAL));
	    	}
	    	else {
	    		new BigDecimal(0);
	    	}
	        return desconto.setScale(2, RoundingMode.HALF_EVEN);
	    }
	
	// 2023-03-28T15: 23:48
	public static String gerarRecibo() {
		LocalDateTime date = LocalDateTime.now();
		String recibo = date.toString().substring(0, 19);
		return recibo.replace("-", "").replace(":", "").replace("T", "-");
		
	}

}
