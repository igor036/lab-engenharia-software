package com.linecode.protocolo.enumerador;

public enum TipoConsultaListaProtocoloEnumerador {
    
    TODOS("T"),
    CODIGO("C"),
    STATUS("S");
    
    private String flag;
    
    TipoConsultaListaProtocoloEnumerador(String flag) {
        this.flag = flag;
    }
    
    public boolean isConsultaTodos() {
        return this == TODOS;
    }
    
    public boolean isConsultaCodigo() {
        return this == CODIGO;
    }
    public boolean isConsultaStatus() {
        return this == STATUS;
    }
    
    public String getFlag() {
        return flag;
    }

    public static TipoConsultaListaProtocoloEnumerador forValue(String flag) {
        for (TipoConsultaListaProtocoloEnumerador enumValue : TipoConsultaListaProtocoloEnumerador.values()) {
            if (enumValue.getFlag().equals(flag)) {
                return enumValue;
            }
        }
        return null;
    }
}
