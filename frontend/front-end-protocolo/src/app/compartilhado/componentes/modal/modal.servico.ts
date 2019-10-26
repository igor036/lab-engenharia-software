/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 23/10/2019
 */
import { Injectable } from '@angular/core';
import { BsModalService, BsModalRef, ModalOptions } from 'ngx-bootstrap';
import { ModalMensagemComponent } from './modal-mensagem/modal-mensagem.component';

enum TituloModal {
  ERRO = 'Erro',
  SUCESSO = 'Sucesso!',
  INFORMACAO = 'Informação',
  ATENCAO = 'Atenção!'
}

enum ClasseComplementarModal {
  ERRO = 'bg-danger',
  SUCESSO = 'bg-success',
  INFORMACAO = 'bg-info',
  ATENCAO = 'bg-warning'
}

@Injectable()
export class ModalServico {

  public bsModalRef: BsModalRef;

  constructor(private modalService: BsModalService) {
  }

  exibirErro(mensagem: string): void {
    this.exibirMensagem(TituloModal.ERRO, mensagem, ClasseComplementarModal.ERRO);
  }

  exibirSucesso(mensagem: string): void {
    this.exibirMensagem(TituloModal.SUCESSO, mensagem, ClasseComplementarModal.SUCESSO);
  }

  exibirInfo(mensagem: string): void {
    this.exibirMensagem(TituloModal.INFORMACAO, mensagem, ClasseComplementarModal.INFORMACAO);
  }

  exibirAtencao(mensagem: string): void {
    this.exibirMensagem(TituloModal.ATENCAO, mensagem, ClasseComplementarModal.ATENCAO);
  }

  private exibirMensagem(titulo: string, mensagem: string, classeComplementar: string): void {
    const initialState = { titulo: titulo, mensagem: mensagem, classeComplementar: classeComplementar };
    this.bsModalRef = this.modalService.show(ModalMensagemComponent, { initialState });
  }
}