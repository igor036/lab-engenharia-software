/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 21/10/2019
 */
package com.linecode.compartilhado.dto;

import java.util.List;

public class PaginacaoDto <T> {
    
    private int paginaAtual;
    private int qtdRegistrosPagina;
    private int qtdTotalRegistros;
    private List<T> lista;
    
    public PaginacaoDto(int paginaAtual, int qtdRegistrosPagina, int qtdTotalRegistros, List<T> lista) {
        this.paginaAtual = paginaAtual;
        this.qtdRegistrosPagina = qtdRegistrosPagina;
        this.qtdTotalRegistros = qtdTotalRegistros;
        this.lista = lista;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public int getQtdRegistrosPagina() {
        return qtdRegistrosPagina;
    }
    
    public int getQtdTotalRegistros() {
        return qtdTotalRegistros;
    }

    public List<T> getLista() {
        return lista;
    }
}
