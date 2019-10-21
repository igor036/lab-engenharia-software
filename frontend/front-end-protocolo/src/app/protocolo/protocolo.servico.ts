import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { CadastrarProtocolo, ConsultarProtocolo } from './protocolo.modelo';
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

const URL_CONTROLADOR: string = "protocolo";

@Injectable()
export class ProtocoloServico {

    constructor(private httpClient: HttpClient) { }

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }

    consultarProtocolo(dados: ConsultarProtocolo): Observable<Array<ConsultarProtocolo>> {
        return of([
            {
                idProtocolo: 12345,
                docente: 'Teste',
                data: '15/01/2019',
                status: 'Aberto'
            },
            {
                idProtocolo: 22345,
                docente: 'Teste',
                data: '16/01/2019',
                status: 'Deferido'
            },
            {
                idProtocolo: 32345,
                docente: 'Teste',
                data: '20/01/2019',
                status: 'Aberto'
            },
            {
                idProtocolo: 42345,
                docente: 'Teste',
                data: '25/01/2019',
                status: 'Deferido'
            },
            {
                idProtocolo: 52345,
                docente: 'Teste',
                data: '30/01/2019',
                status: 'Indeferido'
            }
        ]);
    }
}