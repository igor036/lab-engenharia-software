import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//Modelos
import { Opcao, Filtro, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';

//Servico
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { ConsultarProtocolo } from 'src/app/protocolo/protocolo.modelo';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';


const OPCAO_TODOS: string = 'T';
const OPCAO_CODIGO: string = 'C';
const OPCAO_STATUS: string = 'S';

const STATUS_ABERTO: string = 'ABERTO';
const STATUS_DEFERIDO: string = 'DEFERIDO';
const STATUS_INDEFERIDO: string = 'INDEFERIDO';

@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  protocols = [{ id: 1, docente: 'docente' }, { id: 2, docente: 'docente2' }, { id: 3, docente: 'docente3' }];

  formPesquisaProtocolo: FormGroup;
  listaPesquisaProtocolo: Array<Opcao> = [];
  listaSelecaoDeStatusPesquisa: Array<Opcao> = [];
  filtro: Filtro<ConsultarProtocolo>;
  paginacao: Paginacao;

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
    if (tipo === OPCAO_CODIGO) {
      this.adicionarValidacaoConsultaCodigo();
    } else if (tipo === OPCAO_STATUS) {
      this.adicionarValidacaoConsultaStatus();
    }
  }

  exibirInputDePesquisa(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value === OPCAO_CODIGO;
  }

  exibirSelecaoDeStatus(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value === OPCAO_STATUS;
  }

  pesquisarProtocolo(): void {
    this.selecionarPagina(1);
  }

  selecionarPagina(pagina: number): void {
    //this.spinnerServico.show();
    //this.protocoloServico.consultarProtocolo(pagina, this.filtro).subscribe(protocolos => { this.paginacao = protocolos});

  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      tipo: this.formBuilder.control(OPCAO_TODOS),
      idProtocolo: this.formBuilder.control(''),
      status: this.formBuilder.control('')
    });
  }

  private iniciarListaPesquisaProtocolo(): void {
    this.listaPesquisaProtocolo = [
      { descricao: "Todos", valor: OPCAO_TODOS },
      { descricao: "Código", valor: OPCAO_CODIGO },
      { descricao: "Status", valor: OPCAO_STATUS }
      //
    ];
  }

  private iniciarListaSelecaoStatusPesquisa(): void {
    this.listaSelecaoDeStatusPesquisa = [
      { descricao: "ABERTO", valor: STATUS_ABERTO },
      { descricao: "DEFERIDO", valor: STATUS_DEFERIDO },
      { descricao: "INDEFERIDO", valor: STATUS_INDEFERIDO }
    ]
  }

  private adicionarValidacaoConsultaCodigo(): void {
    let campoIdProtocolo = this.formPesquisaProtocolo.controls.tipo;
    campoIdProtocolo.reset();
    campoIdProtocolo.setValidators([
      Validators.required,
      Validators.pattern('^[1-9]+$')
    ]);
    campoIdProtocolo.updateValueAndValidity();
  }

  private adicionarValidacaoConsultaStatus(): void {
    let campoStatus = this.formPesquisaProtocolo.controls.status;
    campoStatus.reset();
    campoStatus.setValidators([
      Validators.required,
      Validators.pattern('^[1-9]+$')
    ]);
    campoStatus.updateValueAndValidity();
  }

  private limparValidacoesCampos(): void {
    this.formPesquisaProtocolo.controls.idProtocolo.clearValidators();
    this.formPesquisaProtocolo.controls.status.clearValidators();
  }
}
