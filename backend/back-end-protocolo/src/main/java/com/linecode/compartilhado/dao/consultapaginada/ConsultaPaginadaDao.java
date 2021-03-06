/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 21/10/2019
 */
package com.linecode.compartilhado.dao.consultapaginada;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.linecode.compartilhado.dto.PaginacaoDto;

public abstract class ConsultaPaginadaDao<F, V> {

    private static final String STR_REPLACE_FILTRO_ADICIONAL = "\\{filtroAdicional\\}";
    private static final String STR_QUEBRA_LINHA = "\n";

    private String sqlBase;
    private String sqlFiltroAdicional;
    private JdbcTemplate jdbcTemplate;
    private int paginaAtual;
    private int qtdRegistrosPagina;
    protected F filtro;
    private List<Object> listaValorParametros;
    private List<Integer> listaTipoParameteros;

    public ConsultaPaginadaDao(String sqlBase, JdbcTemplate jdbcTemplate, F filtro, int paginaAtual,
            int qtdRegistrosPagina) {
        this.sqlBase = sqlBase;
        this.jdbcTemplate = jdbcTemplate;
        this.filtro = filtro;
        this.listaValorParametros = new ArrayList<>();
        this.paginaAtual = paginaAtual;
        this.qtdRegistrosPagina = qtdRegistrosPagina;
        this.listaTipoParameteros = new ArrayList<>();
        this.sqlFiltroAdicional = getSqlFiltroAdicional();
    }

    /**
     * Adiciona um determinado parametro para a execucao da query
     * 
     * @param parametro - o valor do parametro {@link Object}
     * @param nome      - nome do parametro {@link String}
     * @param tipo      - tipo do parametro {@link Int} em {@link sql.Types}
     */
    protected void adicionarParametro(Object parametro, int tipo) {
        listaValorParametros.add(parametro);
        listaTipoParameteros.add(tipo);
    }

    /**
     * Executa a consulta e retorna a paginacao da consulta corrente.
     * 
     * @return paginacao {@link PaginacaoDto<V>}
     */
    public PaginacaoDto<V> getPaginacao() {
        int qtdTotalRegistros = getQtdTotalRegistros();
        List<V> listaRetorno = jdbcTemplate.query(gerarSqlFinalComPaginacao(), listaValorParametros.toArray(),
                getVetorListaTipoParametro(), this::mapRow);
        return new PaginacaoDto<>(paginaAtual, qtdRegistrosPagina, qtdTotalRegistros, listaRetorno);
    }

    /**
     * Retorna o sql final da consulta apartir do <b>sqlBase</b> o <b>sqlFiltroAdicional</b>
     */
    private String gerarSqlFinal() {
        return sqlBase.replaceFirst(STR_REPLACE_FILTRO_ADICIONAL, sqlFiltroAdicional);
    }

    /**
     * Gera o sql final com paginacao apartir do <b>sqlBase</b> o <b>sqlFiltroAdicional</b> sendo adicionado os
     * parametros: <b>LIMIT</b> = <b>qtdRegistrosPagina</b>. <b>OFFSET</b> = <b>paginaAtual-1</b> (O POSTGRESQL COMECA A
     * CONTAGEM POR 0).
     * 
     * @return sql final com paginacao {@link String}
     */
    private String gerarSqlFinalComPaginacao() {

        StringBuilder sqlFinalComPaginacao = new StringBuilder();
        sqlFinalComPaginacao.append(gerarSqlFinal());
        sqlFinalComPaginacao.append(STR_QUEBRA_LINHA);
        sqlFinalComPaginacao.append("LIMIT ");
        sqlFinalComPaginacao.append(String.valueOf(qtdRegistrosPagina));
        sqlFinalComPaginacao.append(STR_QUEBRA_LINHA);
        sqlFinalComPaginacao.append("OFFSET ");
        sqlFinalComPaginacao.append(String.valueOf(paginaAtual-1));
        
        return sqlFinalComPaginacao.toString();
    }

    /**
     * Metodo responsavel por consultar e retornar a quantidade de registros total da consulta.
     * 
     * @return quantidade de registros totais {@link Integer}
     */
    private int getQtdTotalRegistros() {

        StringBuilder sqlCount = new StringBuilder();

        sqlCount.append("SELECT COUNT (*) FROM (");
        sqlCount.append(STR_QUEBRA_LINHA);
        sqlCount.append(gerarSqlFinal());
        sqlCount.append(STR_QUEBRA_LINHA);
        sqlCount.append(") AS CONSULTA");

        return jdbcTemplate.queryForObject(sqlCount.toString(), listaValorParametros.toArray(),
                getVetorListaTipoParametro(), Integer.class);
    }

    /**
     * Retorna a lista de tipo de parametro em forma de {@link int[]}
     */
    private int[] getVetorListaTipoParametro() {

        int[] vetorTipo = new int[listaTipoParameteros.size()];

        for (int i = 0; i < listaTipoParameteros.size(); i++) {
            vetorTipo[i] = listaTipoParameteros.get(i);
        }

        return vetorTipo;
    }

    /**
     * Metodo responsavel por retornar o codigo <b>SQL</b> do filtro adicional
     * 
     * @return sql filtro adicional.
     */
    protected abstract String getSqlFiltroAdicional();

    /**
     * Metodo utilizado para mapear as linas da lista de retorno.
     * 
     * @throws SQLException
     * 
     */
    protected abstract V mapRow(ResultSet rs, int rowNum) throws SQLException;
}
