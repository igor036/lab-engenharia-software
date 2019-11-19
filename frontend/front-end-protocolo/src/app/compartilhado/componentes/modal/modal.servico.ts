/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 23/10/2019
 */
import { Injectable, Injector } from '@angular/core';
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
  private configPadrao: ModalOptions


  constructor(
    private modalService: BsModalService,
    private injector: Injector) {
    this.configPadrao = {
      animated: true,
      keyboard: true,
      backdrop: "static"
    }
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

  exibirConfirmacao(mensagem: string, dados: any, funcaoConfirmado: Function, funcaoNaoConfirmado?: Function): void {
    //this.configurarModalConfirmacao('modal-warning', mensagem, dados, funcaoConfirmado, funcaoNaoConfirmado)
  }

  private exibirMensagem(titulo: string, mensagem: string, classeComplementar: string): void {
    const initialState = { titulo: titulo, mensagem: mensagem, classeComplementar: classeComplementar };
    this.bsModalRef = this.modalService.show(ModalMensagemComponent, { initialState });
  }

}