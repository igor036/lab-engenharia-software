import { Component, OnInit, EventEmitter } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-modal-confirmacao',
  templateUrl: './modal-confirmacao.component.html',
  styleUrls: ['./modal-confirmacao.component.scss']
})
export class ModalConfirmacaoComponent {

  public listaMsg: Array<string> = [];
  public classIconeCor: string;
  public dados: any;

  public eventoConfirmado = new EventEmitter<string>()
  public eventoNaoConfirmado = new EventEmitter<string>()

  constructor(
    public bsModalRef: BsModalRef
  ) { }

  opcaoConfirmar(): void {
    this.eventoConfirmado.emit(this.dados);
    this.bsModalRef.hide();
  }

  opcaoRescusar(): void {
    this.eventoNaoConfirmado.emit(this.dados);
    this.bsModalRef.hide();
  }

}
