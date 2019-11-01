package com.linecode.protocolo.filtro;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linecode.protocolo.enumerador.CategoriaProtocoloConsultadoEnumerador;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;

public class ConsultaListaProtocoloFiltro {
    
	@NotNull(message = "Informe o tipo de consulta.")
    private TipoConsultaListaProtocoloEnumerador tipo;
	private CategoriaProtocoloConsultadoEnumerador categoria;
    private Long idStatus;
    private Long idProtocolo;
    
    @JsonIgnore
    private long idDocente;
    
    
    public ConsultaListaProtocoloFiltro() {
    	//construtor utilizado pelo jackson
    }

	public ConsultaListaProtocoloFiltro(TipoConsultaListaProtocoloEnumerador tipo,
	        CategoriaProtocoloConsultadoEnumerador categoria,
	        Long idStatus, Long idProtocolo) {
		this.tipo = tipo;
		this.idStatus = idStatus;
		this.idProtocolo = idProtocolo;
	}

	public CategoriaProtocoloConsultadoEnumerador getCategoria() {
	    return this.categoria;
	}
	
	public void setCategoria(String categoria) {
	    this.categoria = CategoriaProtocoloConsultadoEnumerador.forValue(categoria);
	}
	
	public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long status) {
        this.idStatus = status;
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
