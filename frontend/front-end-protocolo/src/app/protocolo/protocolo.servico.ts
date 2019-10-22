import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { CadastrarProtocolo, ConsultarProtocolo } from './protocolo.modelo';
import { Opcao, Filtro, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';

const URL_CONTROLADOR: string = "protocolo";

@Injectable()
export class ProtocoloServico {

    constructor(private httpClient: HttpClient) { }

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }

    consultarProtocolo(pagina: number, filtro: Filtro<ConsultarProtocolo>): Observable<Array<Paginacao>> {
        let param: any = filtro;
        return this.httpClient.get<Array<Paginacao>>(`${URL_CONTROLADOR}/lista-protocolo-docente-logado/pagina-atual/${pagina}/quantidade-registros-pagina/${10}`, 
        {
            params: param
        })
    }
}
