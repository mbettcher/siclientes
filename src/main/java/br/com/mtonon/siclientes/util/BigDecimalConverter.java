package br.com.mtonon.siclientes.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {
	
	public BigDecimal converterStringParaBigDecimal(String value) {
		
		if(value ==  null) {
			return null;
		}
		
		value = value.replace(".", "").replace(",", ".");
		return new BigDecimal(value);
	}

}
