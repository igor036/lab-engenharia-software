package com.linecode.protocolo.enumerador;

public enum StatusProtocoloEnumerador {

    ABERTO("ABERTO"),
    FECHADO("DEFERIDO"),
    INDEFERIDO("INDEFERIDO");
    
    private String flag;
    
    StatusProtocoloEnumerador(String flag) {
        this.flag = flag;
    }
    
    public String getValorTexto() {
        return flag;
    }
    
    public static StatusProtocoloEnumerador forValue(String flag) {
        for (StatusProtocoloEnumerador enumValue : StatusProtocoloEnumerador.values()) {
            if (enumValue.flag.equals(flag)) {
                return enumValue;
            }
        }
        return null;
    }
}
