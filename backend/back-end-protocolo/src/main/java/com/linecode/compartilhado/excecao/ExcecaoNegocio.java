/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.compartilhado.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcecaoNegocio extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ExcecaoNegocio(String msg) {
        super(msg);
    }
}
