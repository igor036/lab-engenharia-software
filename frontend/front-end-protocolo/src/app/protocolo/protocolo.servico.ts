import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { CadastrarProtocolo } from './protocolo.modelo';
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

const URL_CONTROLADOR: string = "protocolo"; 

@Injectable()
export class ProtocoloServico {

    constructor(private httpClient: HttpClient) {}

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }
}