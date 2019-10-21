package com.linecode.protocolo.dto;

public class ListagemProtocoloDto {
    
    private long id;
    private String nomeDocente;
    private String dataInicio;
    private String dataFim;
    
    public ListagemProtocoloDto(long id, String nomeDocente, String dataInicio, String dataFim) {
        this.id = id;
        this.nomeDocente = nomeDocente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public long getId() {
        return id;
    }

    public String getNomeDocente() {
        return nomeDocente;
    }


    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    } 
}
