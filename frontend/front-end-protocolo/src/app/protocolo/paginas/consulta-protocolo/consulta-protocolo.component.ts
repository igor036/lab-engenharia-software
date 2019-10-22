import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//Modelos
import { Opcao, Filtro, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';

//Servico
import { ProtocoloServico } from '../../protocolo.servico';
import { ConsultarProtocolo } from '../../protocolo.modelo';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';


@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  private readonly OPCAO_TODOS: string = 'T';
  private readonly OPCAO_CODIGO: string = 'C';
  private readonly OPCAO_STATUS: string = 'S';

  private readonly STATUS_ABERTO: string = 'ABERTO';
  private readonly STATUS_DEFERIDO: string = 'DEFERIDO';
  private readonly STATUS_INDEFERIDO: string = 'INDEFERIDO';

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


  selecionarTipo(tipo: string) {
    let validacoes;
    let campoValidator = this.formPesquisaProtocolo.controls.tipo;

    if (tipo === this.OPCAO_TODOS || tipo === this.OPCAO_STATUS) {
      validacoes = [];
    } else {
      validacoes = this.getValidacaoCodigo();
    }

    campoValidator.setValue('');
    campoValidator.setValidators(validacoes);
    campoValidator.updateValueAndValidity();
  }

  exibirInputDePesquisa(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value === this.OPCAO_CODIGO;
  }

  exibirSelecaoDeStatus(): boolean {
    return this.formPesquisaProtocolo.controls.tipo.value === this.OPCAO_STATUS;
  }

  pesquisarProtocolo(): void {
    this.selecionarPagina(1);
    console.log("Pesquisar")
  }
  
  selecionarPagina(pagina: number): void {
    //this.spinnerServico.show();
    //this.protocoloServico.consultarProtocolo(pagina, this.filtro).subscribe(protocolos => { this.paginacao = protocolos});
    
  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      tipo: this.formBuilder.control(this.OPCAO_TODOS),
      idProtocolo: this.formBuilder.control(''),
      status: this.formBuilder.control('')
    });
  }

  private iniciarListaPesquisaProtocolo(): void {
    this.listaPesquisaProtocolo = [
      { descricao: "Todos", valor: this.OPCAO_TODOS },
      { descricao: "Código", valor: this.OPCAO_CODIGO },
      { descricao: "Status", valor: this.OPCAO_STATUS }
      //
    ];
  }

  private iniciarListaSelecaoStatusPesquisa(): void {
    this.listaSelecaoDeStatusPesquisa = [
      { descricao: "ABERTO", valor: this.STATUS_ABERTO },
      { descricao: "DEFERIDO", valor: this.STATUS_DEFERIDO },
      { descricao: "INDEFERIDO", valor: this.STATUS_INDEFERIDO }
    ]
  }

  private getValidacaoCodigo(): Array<any> {
    return [
      Validators.required,
      Validators.minLength(1)
    ];
  }


}
