/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Aplicacao extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}

}
