package com.iw3.restaurante.utils;

import javax.persistence.AttributeConverter;

public class TiempoConverter implements AttributeConverter<Tiempo, String> {

	@Override
	public String convertToDatabaseColumn(Tiempo attribute) {		
		return attribute.toString();
	}

	@Override
	public Tiempo convertToEntityAttribute(String dbData) {
		return new Tiempo(dbData);
	}

}