package com.linecode.bdd.protocolo.filtro;

import com.linecode.protocolo.enumerador.CategoriaProtocoloConsultadoEnumerador;
import com.linecode.protocolo.enumerador.TipoConsultaListaProtocoloEnumerador;
import com.linecode.protocolo.filtro.ConsultaListaProtocoloFiltro;

public class ConsultaListaProtocoloTestFiltro extends ConsultaListaProtocoloFiltro {

	private String msgEsperada;
	
	public ConsultaListaProtocoloTestFiltro(TipoConsultaListaProtocoloEnumerador tipo, long idStatus,
			long idProtocolo, String msgEsperada) {
		super(tipo, CategoriaProtocoloConsultadoEnumerador.AVALIAR, idStatus, idProtocolo);
		this.msgEsperada = msgEsperada;
	}
	
	public String getMsgEsperada() {
		return msgEsperada;
	}
}
