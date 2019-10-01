/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
import { Injectable, ErrorHandler } from "@angular/core";
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Injectable()
export class ErrorInterceptor implements ErrorHandler {

  private readonly msgErroInesperado: string = "Erro inesperado!"

  constructor(
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  handleError(error: any): void {
    console.log(error);
    let msg = typeof error.error == 'string' ? error.error : error.error.message;
    alert(msg ? msg : this.msgErroInesperado);
    this.spinnerServico.hide();
    throw error;
  }
}