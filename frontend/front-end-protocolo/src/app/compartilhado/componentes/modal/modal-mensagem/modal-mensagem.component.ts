import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'app-modal-mensagem',
  templateUrl: './modal-mensagem.component.html'
})
export class ModalMensagemComponent implements OnInit {

  public titulo: string;
  public mensagem: string;
  public classeComplementar: string;

  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit() {
  }

}
