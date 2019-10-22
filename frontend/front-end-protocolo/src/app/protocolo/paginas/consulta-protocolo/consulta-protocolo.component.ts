import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//Modelos
import { Opcao, Filtro, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';
import { StatusProtocolo, TipoConsultaListaProtocolo } from 'src/app/protocolo/protocolo.modelo';

//Servico
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { ConsultarProtocolo } from 'src/app/protocolo/protocolo.modelo';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  protocols = [{ id: 1, docente: 'docente' }, { id: 2, docente: 'docente2' }, { id: 3, docente: 'docente3' }];

  public formPesquisaProtocolo: FormGroup;
  public listaPesquisaProtocolo: Array<Opcao> = [];
  public listaSelecaoDeStatusPesquisa: Array<Opcao> = [];
  public filtro: Filtro<ConsultarProtocolo>;
  public paginacao: Paginacao;

  constructor(
    private formBuilder: FormBuilder,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.iniciarListaPesquisaProtocolo();
    this.iniciarFormPesquisaProtocolo();
    this.iniciarListaSelecaoStatusPesquisa();
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
    this.selecionarPagina(1);
  }

  selecionarPagina(pagina: number): void {

  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      tipo: this.formBuilder.control(TipoConsultaListaProtocolo.OPCAO_TODOS),
      idProtocolo: this.formBuilder.control(''),
      status: this.formBuilder.control('')
    });
  }

  private iniciarListaPesquisaProtocolo(): void {
    this.listaPesquisaProtocolo = [
      { descricao: "Todos", valor: TipoConsultaListaProtocolo.OPCAO_TODOS },
      { descricao: "Código", valor: TipoConsultaListaProtocolo.OPCAO_CODIGO },
      { descricao: "Status", valor: TipoConsultaListaProtocolo.OPCAO_STATUS }
    ];
  }

  private iniciarListaSelecaoStatusPesquisa(): void {
    this.listaSelecaoDeStatusPesquisa = [
      { descricao: "Aberto", valor: StatusProtocolo.STATUS_ABERTO },
      { descricao: "Deferido", valor: StatusProtocolo.STATUS_DEFERIDO },
      { descricao: "Indeferido", valor: StatusProtocolo.STATUS_INDEFERIDO }
    ]
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
