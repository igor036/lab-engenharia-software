import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//Modelos
import { Opcao, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';
import { TipoConsultaListaProtocolo, ConsultaListaProtocolo } from 'src/app/protocolo/protocolo.modelo';
import { PAGINACAO_PADRAO } from 'src/app/app.constante';

//Servico
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { UtilServico } from 'src/app/compartilhado/util.servico';

@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  public paginacao: Paginacao = PAGINACAO_PADRAO;
  public listaOpcaoTipoConsulta: Array<Opcao> = [];
  public listaOpcaoStatus: Array<Opcao> = [];
  public formPesquisaProtocolo: FormGroup;
  public filtro: ConsultaListaProtocolo;


  constructor(
    private formBuilder: FormBuilder,
    private utilServico: UtilServico,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.iniciarListaOpcaoTipoConsulta();
    this.iniciarFormPesquisaProtocolo();
    this.iniciarListaOpcaoStatus();
    this.pesquisarProtocolo();
  }

  selecionarTipoConsulta(tipo: string) {
    this.limparValidacoesCampos();
    if (tipo === TipoConsultaListaProtocolo.OPCAO_CODIGO) {
      this.adicionarValidacaoConsultaCodigo();
    } else if (tipo === TipoConsultaListaProtocolo.OPCAO_STATUS) {
      this.adicionarValidacaoConsultaStatus();
    }
  }

  exibirInputDePesquisa(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value == TipoConsultaListaProtocolo.OPCAO_CODIGO;
  }

  exibirSelecaoDeStatus(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value == TipoConsultaListaProtocolo.OPCAO_STATUS;
  }

  pesquisarProtocolo(): void {
    this.filtro = this.formPesquisaProtocolo.value;
    this.selecionarPagina(1);
  }

  selecionarPagina(pagina: number): void {
    this.spinnerServico.show();
    this.protocoloServico.getListaProtocoloDocenteLogado(this.filtro, pagina).subscribe(paginacao => {
      this.paginacao = paginacao;
      this.spinnerServico.hide();
    });
  }

  exibirListaProtocolo(): boolean {
    return this.paginacao.lista.length > 0;
  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      tipo: this.formBuilder.control(TipoConsultaListaProtocolo.OPCAO_TODOS),
      idProtocolo: this.formBuilder.control(''),
      idStatus: this.formBuilder.control('')
    });
  }

  private iniciarListaOpcaoTipoConsulta(): void {
    this.listaOpcaoTipoConsulta = [
      { descricao: "Todos", valor: TipoConsultaListaProtocolo.OPCAO_TODOS },
      { descricao: "Código", valor: TipoConsultaListaProtocolo.OPCAO_CODIGO },
      { descricao: "Status", valor: TipoConsultaListaProtocolo.OPCAO_STATUS }
    ];
  }

  private iniciarListaOpcaoStatus(): void {
    this.spinnerServico.show();
    this.utilServico.getListaStatusProtocolo().subscribe(listaOpcaoStatus => {
      this.listaOpcaoStatus = listaOpcaoStatus;
      this.spinnerServico.hide();
    });
  }

  private adicionarValidacaoConsultaCodigo(): void {
    let campoIdProtocolo = this.formPesquisaProtocolo.controls.idProtocolo;
    campoIdProtocolo.reset();
    campoIdProtocolo.setValidators([
      Validators.required,
      Validators.pattern('^[1-9]+$')
    ]);
    campoIdProtocolo.updateValueAndValidity();
  }

  private adicionarValidacaoConsultaStatus(): void {
    let campoStatus = this.formPesquisaProtocolo.controls.status;
    campoStatus.setValue('');
    campoStatus.setValidators(Validators.required);
    campoStatus.updateValueAndValidity();
  }

  private limparValidacoesCampos(): void {
    this.formPesquisaProtocolo.controls.idProtocolo.clearValidators();
    this.formPesquisaProtocolo.controls.status.clearValidators();
  }
}
