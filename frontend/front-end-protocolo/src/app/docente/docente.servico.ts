/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

import { PROPRIEDADES, URLS_NAMES } from 'src/app/app.constante';
import { Observable, of, Observer } from 'rxjs';

import {
    DocenteLogado,
    Login,
    CadastroDocente
} from './docente.modelo';
import { ListarSugestoesDePareceristas } from './docente.modelo';
import { Opcao } from '../compartilhado/compartilhado.modelo';
@Injectable()
export class DocenteServico {

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

    setTokenDocente(token: string): void {
        localStorage.setItem(PROPRIEDADES.TOKEN_DOCENTE, token);
    }

    setDocenteLogado(docenteLogado: DocenteLogado): void {
        localStorage.setItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO, JSON.stringify(docenteLogado));
    }

    deslogar(): void {
        localStorage.removeItem(PROPRIEDADES.TOKEN_DOCENTE);
        localStorage.removeItem(PROPRIEDADES.DADOS_DOCENTE_LOGADO);
        this.router.navigate([URLS_NAMES.login]);
    }

    getListaSugestaoDocente(descricao: string): Observable<Array<Opcao>> {
        return this.httpClient.get<Array<Opcao>>(`${this.URL_CONTROLADOR}/lista-sugestao-docente/${descricao}`)
    }
}