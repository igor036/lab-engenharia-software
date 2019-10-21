package com.linecode.protocolo.dto;

import java.util.ArrayList;
import java.util.List;

public class DetalheProtocoloDto {
    
    private long id;
    private long matriculaDocente;
    private String nomeDoscente;
    private Long matriculaAvaliador;
    private String nomeAvaliador;
    private String resumo;
    private String justificativa;
    private List<DetalhePedidoProtocoloDto> pedidos;
    
    public DetalheProtocoloDto(long id, long matriculaDocente, String nomeDoscente, Long matriculaAvaliador,
            String nomeAvaliador, String resumo, String justificativa) {
        this.id = id;
        this.matriculaDocente = matriculaDocente;
        this.nomeDoscente = nomeDoscente;
        this.matriculaAvaliador = matriculaAvaliador;
        this.nomeAvaliador = nomeAvaliador;
        this.resumo = resumo;
        this.justificativa = justificativa;
        this.pedidos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getMatriculaDocente() {
        return matriculaDocente;
    }

    public String getNomeDoscente() {
        return nomeDoscente;
    }

    public Long getMatriculaAvaliador() {
        return matriculaAvaliador;
    }

    public String getNomeAvaliador() {
        return nomeAvaliador;
    }

    public String getResumo() {
        return resumo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public List<DetalhePedidoProtocoloDto> getPedidos() {
        return pedidos;
    }
}
