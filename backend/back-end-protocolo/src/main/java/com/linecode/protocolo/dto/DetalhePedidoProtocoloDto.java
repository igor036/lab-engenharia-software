package com.linecode.protocolo.dto;

public class DetalhePedidoProtocoloDto {
    
    private String especie;
    private int quantidade;
    private String bioterio;
    
    public DetalhePedidoProtocoloDto(String especie, int quantidade, String bioterio) {
        this.especie = especie;
        this.quantidade = quantidade;
        this.bioterio = bioterio;
    }

    public String getEspecie() {
        return especie;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getBioterio() {
        return bioterio;
    }
}
