/**
 * Author: Igor Joaquim dos Santos Lima 
 * Date: 19/10/2019
 */
package com.linecode.compartilhado.servico;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DataServico {
	
	public Date localDateParaData(LocalDate data) {
		return java.util.Date.from(data.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	public java.sql.Date localDateParaSqlDate(LocalDate data) {
		return new java.sql.Date(localDateParaData(data).getTime());
	}
}
