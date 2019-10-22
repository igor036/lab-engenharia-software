package com.linecode.protocolo.dto;

import java.util.ArrayList;
import java.util.List;

public class DetalheProtocoloDto {

    private long id;
    private long matriculaDocente;
    private String nomeDoscente;
    private Long matriculaAvaliador;
    private String nomeAvaliador;
    private String resumoPt;
    private String resumoEn;
    private String justificativa;
    private Boolean permitido;
    private String observacaoParecer;
    private List<DetalhePedidoProtocoloDto> pedidos;

    public DetalheProtocoloDto(long id, long matriculaDocente, String nomeDoscente, Long matriculaAvaliador,
            String nomeAvaliador, String resumoPt, String resumoEn, String justificativa, Boolean permitido,
            String observacaoParecer) {
        this.id = id;
        this.matriculaDocente = matriculaDocente;
        this.nomeDoscente = nomeDoscente;
        this.matriculaAvaliador = matriculaAvaliador;
        this.nomeAvaliador = nomeAvaliador;
        this.resumoPt = resumoPt;
        this.resumoEn = resumoEn;
        this.justificativa = justificativa;
        this.permitido = permitido;
        this.observacaoParecer = observacaoParecer;
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

    public String getResumoPt() {
        return resumoPt;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getResumoEn() {
        return resumoEn;
    }

    public Boolean getPermitido() {
        return permitido;
    }

    public String getObservacaoParecer() {
        return observacaoParecer;
    }

    public List<DetalhePedidoProtocoloDto> getPedidos() {
        return pedidos;
    }
}
