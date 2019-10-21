import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

//Modelos
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

//Servico
import { ProtocoloServico } from '../../protocolo.servico';


@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  private readonly OPCAO_TODOS: string = 'T';
  private readonly OPCAO_CODIGO: string = 'C';
  private readonly OPCAO_STATUS: string = 'S';

  private readonly STATUS_ABERTO: string = 'Aberto';
  private readonly STATUS_DEFERIDO: string = 'Deferido';
  private readonly STATUS_INDEFERIDO: string = 'Indeferido';

  protocols = [{id:1,docente:'docente'},{id:2,docente:'docente2'},{id:3,docente:'docente3'}];

  formPesquisaProtocolo: FormGroup;
  listaPesquisaProtocolo: Array<Opcao> = [];
  listaSelecaoDeStatusPesquisa: Array<Opcao> = [];

  constructor(
    private formBuilder: FormBuilder,
    private protocoloServico: ProtocoloServico
  ) { }

  ngOnInit() {
    this.iniciarListaPesquisaProtocolo();
    this.iniciarFormPesquisaProtocolo();
    this.iniciarListaSelecaoStatusPesquisa();
  }


  selecionarTipo(campo: string) {
    if (campo === 'Todos') {

    }
  }

  exibirInputDePesquisa(): boolean {
    return this.formPesquisaProtocolo.controls.campo.value === this.OPCAO_CODIGO;
  }

  exibirSelecaoDeStatus(): boolean {
    return this.formPesquisaProtocolo.controls.campo.value === this.OPCAO_STATUS;
  }

  pesquisarProtocolo(): void {
    //this.protocoloServico.consultarProtocolo()
    console.log("Pesquisar")
  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      campo: this.formBuilder.control(this.OPCAO_TODOS),
      valor: this.formBuilder.control('')
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


}
