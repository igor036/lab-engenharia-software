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

  public exibirPaginacao(): boolean {
    return this.paginacao.qtdTotalRegistros / this.paginacao.qtdRegistrosPagina > 1;
  }

  public pageChanged(pagina: number): void {
    this.selecionarPagina.emit(pagina);
  }
}
