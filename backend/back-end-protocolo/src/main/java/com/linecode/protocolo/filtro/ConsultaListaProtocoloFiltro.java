package com.linecode.protocolo.filtro;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linecode.protocolo.enumerador.StatusProtocoloEnumerador;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;

public class ConsultaListaProtocoloFiltro {
    
	@NotNull(message = "Informe o tipo de consulta.")
    private TipoConsultaListaProtocoloEnumerador tipo;
    private StatusProtocoloEnumerador status;
    private Long idProtocolo;
    
    @JsonIgnore
    private long idDocente;
    
    
    public ConsultaListaProtocoloFiltro() {
    	//construtor utilizado pelo jackson
    }

	public ConsultaListaProtocoloFiltro(TipoConsultaListaProtocoloEnumerador tipo,
			StatusProtocoloEnumerador status, long idProtocolo) {
		this.tipo = tipo;
		this.status = status;
		this.idProtocolo = idProtocolo;
	}

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

    public Long getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(Long codigoProtocolo) {
        this.idProtocolo = codigoProtocolo;
    }

    public long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(long idDocente) {
        this.idDocente = idDocente;
    }
}
