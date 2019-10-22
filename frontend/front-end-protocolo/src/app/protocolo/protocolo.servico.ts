import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

//terceiros
import { Observable } from 'rxjs';

//modelos
import { QUANTIDADE_REGISTROS_PAGINA_PADRAO } from 'src/app/app.constante';
import { Paginacao } from 'src/app/compartilhado/compartilhado.modelo';
import { CadastrarProtocolo, ConsultaListaProtocolo, DetalheProtocolo } from './protocolo.modelo';

//utilitarios
import { HttpUtil } from 'src/app/compartilhado/httpUtil';

const URL_CONTROLADOR: string = "protocolo";

@Injectable()
export class ProtocoloServico {

    constructor(private httpClient: HttpClient) { }

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }

    getListaProtocoloDocenteLogado(filtro: ConsultaListaProtocolo, paginaAtual: number): Observable<Paginacao> {
        return this.httpClient.get<Paginacao>(
            `${URL_CONTROLADOR}/lista-protocolo-docente-logado/pagina-atual/${paginaAtual}/quantidade-registros-pagina/${QUANTIDADE_REGISTROS_PAGINA_PADRAO}`,
            { params: HttpUtil.converterObjetoParaHttpParametros(filtro) }
        );
    }

    getDetalheProtocolo(idProtocolo: number): Observable<DetalheProtocolo> {
        return this.httpClient.get<DetalheProtocolo>(`${URL_CONTROLADOR}/detalhe/${idProtocolo}`);
    }
}
