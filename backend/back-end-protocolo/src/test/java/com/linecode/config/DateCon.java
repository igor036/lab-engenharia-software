package com.linecode.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import cucumber.deps.com.thoughtworks.xstream.converters.Converter;
import cucumber.deps.com.thoughtworks.xstream.converters.MarshallingContext;
import cucumber.deps.com.thoughtworks.xstream.converters.UnmarshallingContext;
import cucumber.deps.com.thoughtworks.xstream.io.HierarchicalStreamReader;
import cucumber.deps.com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateCon implements Converter {

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	@Override
	public boolean canConvert(Class type) {
		return LocalDate.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		LocalDate date = (LocalDate) value;
		String result = date.format(DATE_FORMAT);
		writer.setValue(result);
		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		String value = reader.getValue();
		
		if (StringUtils.isEmpty(value) || value == null) {
			return null;
		}
		
		LocalDate result = LocalDate.parse(reader.getValue(), DATE_FORMAT);
		return result;
	}

}
