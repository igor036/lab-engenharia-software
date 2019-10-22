import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

//modelo
import { DetalheProtocolo } from 'src/app/protocolo/protocolo.modelo';

//servico
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';

const OPCAO_RESUMO_PT: string = 'PT';
const OPCAO_RESUMO_EN: string = 'EN';

@Component({
  selector: 'app-detalhe-protocolo',
  templateUrl: './detalhe-protocolo.component.html',
  styleUrls: ['./detalhe-protocolo.component.scss']
})
export class DetalheProtocoloComponent implements OnInit {

  private readonly ID_PROTOCOLO: number;
  private opcaoResumo: string = OPCAO_RESUMO_PT;

  public detalheProtocolo: DetalheProtocolo;

  constructor(
    private rota: ActivatedRoute,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) {
    this.ID_PROTOCOLO = this.rota.snapshot.params['idProtocolo'];
  }

  ngOnInit() {
    this.carregarDetalhesProtocolo();
  }

  getTextoBotaoResumo(): string {
    return this.opcaoResumo == OPCAO_RESUMO_PT ?
      'Ver em inglês' : 'Ver em Portugês';
  }

  alterarOpcaoResumo(): void {
    this.opcaoResumo = this.opcaoResumo == OPCAO_RESUMO_PT ? OPCAO_RESUMO_EN : OPCAO_RESUMO_PT;
  }

  exibirResumoPortugues(): boolean {
    return this.opcaoResumo == OPCAO_RESUMO_PT;
  }

  exibirResumoIngles(): boolean {
    return this.opcaoResumo == OPCAO_RESUMO_EN;
  }

  exibirAvaliador(): boolean {
    if (this.detalheProtocolo && this.detalheProtocolo.nomeAvaliador) {
      return true;
    }
    return false;
  }

  exibirParecer(): boolean {
    if (this.detalheProtocolo && this.detalheProtocolo.permitido != undefined) {
      return true;
    }
    return false;
  }

  getTextoPermitidoParecer(): string {
    if (this.exibirParecer()) {
      return this.detalheProtocolo.permitido ? 'Sim' : 'Não';
    }
    return '';
  }

  private carregarDetalhesProtocolo(): void {
    this.spinnerServico.show();
    this.protocoloServico.getDetalheProtocolo(this.ID_PROTOCOLO).subscribe(detalheProtocolo => {
      this.detalheProtocolo = detalheProtocolo;
      this.spinnerServico.hide();
    });
  }
}
