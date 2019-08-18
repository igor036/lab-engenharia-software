import { Injectable } from "@angular/core";

import { PROPRIEDADES } from 'src/app/app.constante';

@Injectable()
export class DocenteServico {

    constructor() {}

    public isLogado(): boolean {
        if (localStorage.getItem(PROPRIEDADES.TOKEN_DOCENTE)) {
            return true;
        }
        return false;
    }
}