import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

//modelo
import { DetalheProtocolo } from 'src/app/protocolo/protocolo.modelo';

//servico
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { DocenteServico } from 'src/app/docente/docente.servico';
import { DocenteLogado } from 'src/app/docente/docente.modelo';

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
  private docenteLogado: DocenteLogado;
  public detalheProtocolo: DetalheProtocolo;


  constructor(
    private rota: ActivatedRoute,
    private docenteServico: DocenteServico,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) {
    this.ID_PROTOCOLO = this.rota.snapshot.params['idProtocolo'];
  }

  ngOnInit() {
    this.carregarDetalhesProtocoloEDocenteLogado();
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

  exibirBotoesAvaliar(): boolean {
    if (this.detalheProtocolo && this.docenteLogado) {
      return this.detalheProtocolo.matriculaDocente != this.docenteLogado.matricula;
    }
    return false;
  }

  private carregarDetalhesProtocoloEDocenteLogado(): void {

    let respostas = 0;
    let esconderSpinner: Function = () => {
      if (++respostas == 2) {
        this.spinnerServico.hide();
      }
    };

    this.spinnerServico.show();
    this.protocoloServico.getDetalheProtocolo(this.ID_PROTOCOLO).subscribe(detalheProtocolo => {
      this.detalheProtocolo = detalheProtocolo;
      esconderSpinner();
    });

    this.docenteServico.getDadosDocenteLogado().subscribe(docenteLogado => {
      this.docenteLogado = docenteLogado;
      esconderSpinner();
    });
  }
}
