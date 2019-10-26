/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 22/08/2019
 */
package com.linecode.compartilhado.dto;

public class OpcaoDto <T> {

    private T valor;
    private String descricao;
    
    public OpcaoDto(T valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public T getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
