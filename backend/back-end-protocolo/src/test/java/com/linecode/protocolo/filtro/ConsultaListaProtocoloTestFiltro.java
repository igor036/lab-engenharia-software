package com.linecode.protocolo.filtro;

import com.linecode.protocolo.enumerador.StatusProtocoloEnumerador;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;

public class ConsultaListaProtocoloTestFiltro extends ConsultaListaProtocoloFiltro {

	private String msgEsperada;
	
	public ConsultaListaProtocoloTestFiltro(TipoConsultaListaProtocoloEnumerador tipo, StatusProtocoloEnumerador status,
			long idProtocolo, String msgEsperada) {
		super(tipo, status, idProtocolo);
		this.msgEsperada = msgEsperada;
	}
	
	public String getMsgEsperada() {
		return msgEsperada;
	}
}
