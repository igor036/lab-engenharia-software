package com.linecode.protocolo.enumerador;

public enum TipoConsultaListaProtocoloEnumerador {
    
    TODOS("T"),
    CODIGO("C");
    
    private String flag;
    
    TipoConsultaListaProtocoloEnumerador(String flag) {
        this.flag = flag;
    }
    
    public boolean isConsultaTodos() {
        return this == TODOS;
    }
    
    public String getFlag() {
        return flag;
    }
}
