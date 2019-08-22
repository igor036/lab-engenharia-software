/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.compartilhado.excecao;

public class ExcecaoNegocio extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ExcecaoNegocio(String msg) {
        super(msg);
    }
}
