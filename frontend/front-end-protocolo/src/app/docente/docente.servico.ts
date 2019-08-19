/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';

import { PROPRIEDADES } from 'src/app/app.constante';
import { Observable } from 'rxjs';

import { DocenteLogado, Login } from './docente.modeo';

@Injectable()
export class DocenteServico {

    constructor(
        private httpClient: HttpClient
    ) { }

    isLogado(): boolean {
        if (localStorage.getItem(PROPRIEDADES.TOKEN_DOCENTE)) {
            return true;
        }
        return false;
    }

    getTokenlogar(login: Login): Observable<string> {

        const body = new HttpParams()
            .set("email", login.email)
            .set("senha", login.senha);

        return this.httpClient.post('login', body.toString(), {
            headers: { 
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            responseType: 'text'
        });
    }

    getTokenLogado(): string {
        let token = localStorage.getItem(PROPRIEDADES.TOKEN_DOCENTE);
        return token ? token : '';
    }

    getDadosDocenteLogado(): Observable<DocenteLogado> {
        return this.httpClient.get<DocenteLogado>('docente/dados-docente-logado');
    }

    setDocenteLogado(docente: DocenteLogado): void {
        localStorage.setItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO, JSON.stringify(docente));
    }

    setTokenDocente(token: string): void {
        localStorage.setItem(PROPRIEDADES.TOKEN_DOCENTE, token);
    }
}