package com.linecode.protocolo.filtro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linecode.protocolo.enumerador.StatusProtocoloEnumerador;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;

public class ConsultaListaProtocoloFiltro {
    
    private StatusProtocoloEnumerador status;
    private TipoConsultaListaProtocoloEnumerador tipo;
    private long idProtocolo;
    
    @JsonIgnore
    private long idDocente;
    
    public StatusProtocoloEnumerador getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = StatusProtocoloEnumerador.forValue(status);
    }

    public TipoConsultaListaProtocoloEnumerador getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = TipoConsultaListaProtocoloEnumerador.forValue(tipo);
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
