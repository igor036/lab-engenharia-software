/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.publico.excecao;

public class ExcecaoAplicacao extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public ExcecaoAplicacao(String msg, Exception ex) {
        super(msg, ex);
    }
}
