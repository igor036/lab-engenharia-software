import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { CadastrarProtocolo } from './protocolo.modelo';

@Injectable()
export class ProtocoloServico {
    
    private readonly URL_CONTROLADOR: string = "protocolo"; 

    constructor(private httpClient: HttpClient) {}

    cadastrarProtocolo(dados: CadastrarProtocolo): Observable<string> {
        return this.httpClient.post(`${this.URL_CONTROLADOR}/cadastrar`, dados, {
            responseType: 'text'
        });
    }
}