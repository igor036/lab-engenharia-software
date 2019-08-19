/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable } from "@angular/core";
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
} from '@angular/common/http';

import { PROPRIEDADES } from 'src/app/app.constante';
import { DocenteServico } from '../docente/docente.servico';

@Injectable()
export class HttpInterceptorCore implements HttpInterceptor {

    constructor(
        private docenteSerico: DocenteServico
    ){}

    intercept(req: HttpRequest<any>, next: HttpHandler): import("rxjs").Observable<HttpEvent<any>> {
        return next.handle(this.getRequisicaoAutorizacao(req));
    }

    private getRequisicaoAutorizacao(req: HttpRequest<any>): any {
        return req.clone({
            url: PROPRIEDADES.URL_BASE+req.url,
            setHeaders: { Authorization: this.docenteSerico.getTokenLogado() }
        });
    }
}