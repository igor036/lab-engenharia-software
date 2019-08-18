/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';

import { PROPRIEDADES } from 'src/app/app.constante';
import { Observable } from 'rxjs';

import { DocenteLogado, Login } from './docente.modeo';

@Injectable()
export class DocenteServico {

    constructor(
        private httpClient: HttpClient
    ) {}

    isLogado(): boolean {
        if (localStorage.getItem(PROPRIEDADES.TOKEN_DOCENTE)) {
            return true;
        }
        return false;
    }

    logar(login: Login): Observable<DocenteLogado> {
        return this.httpClient.post<DocenteLogado>('http://localhost:808/login', login);
    }

    setDocenteLogado(docente: DocenteLogado): void {
        localStorage.setItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO, JSON.stringify(docente));
    }
}