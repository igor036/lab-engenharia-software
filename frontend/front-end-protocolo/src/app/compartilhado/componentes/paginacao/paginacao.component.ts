import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Paginacao } from 'src/app/compartilhado/compartilhado.modelo';

@Component({
  selector: 'app-paginacao',
  templateUrl: './paginacao.component.html'
})
export class PaginacaoComponent implements OnInit {

  @Input() public paginacao: Paginacao;
  @Output() public selecionarPagina: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

  public pageChanged(event: any): void {
    let emitirEventoAlteracaoPagina = this.paginacao.paginaAtual !== event.page
    this.paginacao.paginaAtual = event.page
    this.paginacao.qtdRegistrosPagina = event.itemsPerPage
    if (emitirEventoAlteracaoPagina) {
      this.selecionarPagina.emit(event.page)
    }
  }

  public mostrarPaginacao(): boolean {
    return this.paginacao.qtdTotalRegistros > this.paginacao.qtdRegistrosPagina;
  }
}
