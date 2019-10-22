/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable, EventEmitter } from "@angular/core";
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { PROPRIEDADES, URLS_NAMES } from 'src/app/app.constante';
import { Observable, of } from 'rxjs';

import {
    DocenteLogado,
    Login,
    CadastroDocente
} from './docente.modelo';

@Injectable()
export class DocenteServico {

    public docenteLogadoEvento: EventEmitter<DocenteLogado> = new EventEmitter<DocenteLogado>();
    private readonly URL_CONTROLADOR: string = 'docente';

    constructor(
        private httpClient: HttpClient,
        private router: Router
    ) { }

    getDadosDocenteLogado(): Observable<DocenteLogado> {

        if (this.isLogado()) {
            return of(JSON.parse(localStorage.getItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO)));
        }

        return this.httpClient.get<DocenteLogado>(
            `${this.URL_CONTROLADOR}/dados-docente-logado`
        );
    }

    cadastrarDocente(cadastroDocente: CadastroDocente): Observable<string> {
        return this.httpClient.post(
            `${this.URL_CONTROLADOR}/cadastrar`, cadastroDocente,
            { responseType: 'text' }
        );
    }

    isLogado(): boolean {
        if (localStorage.getItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO)) {
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

    setDocenteLogado(docente: DocenteLogado): void {
        localStorage.setItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO, JSON.stringify(docente));
        this.docenteLogadoEvento.emit(docente);
    }

    setTokenDocente(token: string): void {
        localStorage.setItem(PROPRIEDADES.TOKEN_DOCENTE, token);
    }

    deslogar(): void {
        localStorage.removeItem(PROPRIEDADES.TOKEN_DOCENTE);
        localStorage.removeItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO);
        this.router.navigate([URLS_NAMES.login]);
    }
}