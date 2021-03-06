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

import { DocenteServico } from 'src/app/docente/docente.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { URL_BASE } from 'src/environments/environment';

@Injectable()
export class HttpInterceptorCore implements HttpInterceptor {

    constructor(
        private docenteSerico: DocenteServico,
        private spinnerServico: Ng4LoadingSpinnerService
    ) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): import("rxjs").Observable<HttpEvent<any>> {
        return next.handle(this.getRequisicaoAutorizacao(req));
    }

    private getRequisicaoAutorizacao(req: HttpRequest<any>): any {
        this.spinnerServico.hide();
        if (req.url == "login") {
            return req.clone({ url: URL_BASE + req.url });
        }

        return req.clone({
            url: URL_BASE + req.url,
            setHeaders: {
                Authorization: this.docenteSerico.getTokenLogado()
            },
        });
    }
}