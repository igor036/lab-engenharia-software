package com.linecode.protocolo.dto;

public class ListagemProtocoloDto {
    
    private long id;
    private String nomeDocente;
    private String dataInicio;
    private String dataFim;
    private String status;
    
    public ListagemProtocoloDto(long id, String nomeDocente, String dataInicio, String dataFim, String status) {
        this.id = id;
        this.nomeDocente = nomeDocente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
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
    
    public String getStatus() {
        return status;
    }
}
