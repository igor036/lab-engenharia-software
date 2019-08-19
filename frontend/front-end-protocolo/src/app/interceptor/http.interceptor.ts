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

@Injectable()
export class HttpInterceptorCore implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): import("rxjs").Observable<HttpEvent<any>> {
        console.log("interceptou")
        return next.handle(this.getRequisicaoAutorizacao(req));
    }

    private getRequisicaoAutorizacao(req: HttpRequest<any>): any {
        return req.clone({
            setHeaders: { Authorization: localStorage.getItem(PROPRIEDADES.TOKEN_DOCENTE) }
        });
    }
}