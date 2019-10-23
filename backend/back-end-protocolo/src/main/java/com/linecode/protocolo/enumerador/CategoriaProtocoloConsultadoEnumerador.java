package com.linecode.protocolo.enumerador;

public enum CategoriaProtocoloConsultadoEnumerador {
    
    DOCENTE_LOGADO("DL"),
    AVALIAR("A"),
    OUTROS_DOCENTES("OD");
    
    private String flag;
    
    CategoriaProtocoloConsultadoEnumerador(String flag) {
        this.flag = flag;
    }
    
    public String getFlag() {
        return flag;
    }
    
    public boolean isDocenteLogado() {
        return this == DOCENTE_LOGADO;
    }
    
    public boolean isAvaliar() {
        return this == AVALIAR;
    }
    
    public boolean isOutrosDocentes() {
        return this == OUTROS_DOCENTES;
    }
    
    public static CategoriaProtocoloConsultadoEnumerador forValue(String flag) {
        for (CategoriaProtocoloConsultadoEnumerador enumValue : CategoriaProtocoloConsultadoEnumerador.values()) {
            if (enumValue.getFlag().equals(flag)) {
                return enumValue;
            }
        }
        return null;
    }
}
