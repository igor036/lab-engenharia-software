/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable, ErrorHandler, Injector } from "@angular/core";
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ModalServico } from '../compartilhado/componentes/modal/modal.servico';

@Injectable()
export class ErrorInterceptor implements ErrorHandler {

  private readonly msgErroInesperado: string = "Erro inesperado!"

  constructor(
    private injector: Injector,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  handleError(error: any): void {
    console.log(error);
    let msg = error.error;
    this.injector.get(ModalServico).exibirErro(msg);
    this.spinnerServico.hide();
  }
}