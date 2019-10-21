package com.linecode.protocolo.filtro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;

public class ConsultaListaProtocoloFiltro {
    
    private TipoConsultaListaProtocoloEnumerador tipo;
    private long idProtocolo;
    
    @JsonIgnore
    private long idDocente;
    
    private ConsultaListaProtocoloFiltro () {
        // construtor utilizado pelo jackson.
    }
    
    public TipoConsultaListaProtocoloEnumerador getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsultaListaProtocoloEnumerador tipo) {
        this.tipo = tipo;
    }

    public long getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(long codigoProtocolo) {
        this.idProtocolo = codigoProtocolo;
    }

    public long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(long idDocente) {
        this.idDocente = idDocente;
    }
}
