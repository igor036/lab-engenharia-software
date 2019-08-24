/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */

//core
import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';

//terceiros
import { Observable } from 'rxjs';

//modelos
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

@Injectable()
export class UtilServico {

    private readonly URL_CONTROLADOR: string = 'util';

    constructor(private httpClient: HttpClient) {}

    getListaPerfil(): Observable<Array<Opcao>> {
        return this.httpClient.get<Array<Opcao>>(
            `${this.URL_CONTROLADOR}/lista-perfil`
        );
    }
}