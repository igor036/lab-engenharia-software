/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 21/08/2019
 */
package com.linecode.publico.enumerador;

public enum PerfilEnumerador {
    
    ADMIN("ADMIN"),
    PROFESSOR("PROFESSOR"),
    SECRETARIA("SECRETARIA"),
    COORDENADOR("COORDENADOR");
    
    private String perfil;
    
    PerfilEnumerador(String perfil) {
        this.perfil = perfil;
    }
    
    @Override
    public String toString() {
        return perfil;
    }
    
    public static PerfilEnumerador getPerfil(String text) {
        for (PerfilEnumerador b : PerfilEnumerador.values()) {
            if (b.perfil.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
    
}
