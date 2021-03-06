import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//Modelos
import { Opcao, Paginacao } from 'src/app/compartilhado/compartilhado.modelo';
import { PAGINACAO_PADRAO, URLS_NAMES, Perfil } from 'src/app/app.constante';
import {
  TipoConsultaListaProtocolo,
  ConsultaListaProtocolo,
  CategoriaProtocoloConsultado
} from 'src/app/protocolo/protocolo.modelo';

//Servico
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { UtilServico } from 'src/app/compartilhado/servico/util.servico';
import { DocenteServico } from 'src/app/docente/docente.servico';

@Component({
  selector: 'app-consulta-protocolo',
  templateUrl: './consulta-protocolo.component.html',
  styleUrls: ['./consulta-protocolo.component.scss']
})
export class ConsultaProtocoloComponent implements OnInit {

  public paginacao: Paginacao = PAGINACAO_PADRAO;
  public listaOpcaoTipoConsulta: Array<Opcao> = [];
  public listaOpcaoStatus: Array<Opcao> = [];
  public listaOpcaoCategoria: Array<Opcao> = [];
  public formPesquisaProtocolo: FormGroup;
  public filtro: ConsultaListaProtocolo;
  public mostrarComboCategoria: boolean = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private utilServico: UtilServico,
    private docenteServico: DocenteServico,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.iniciarListaOpcaoTipoConsulta();
    this.iniciarFormPesquisaProtocolo();
    this.iniciarListaOpcaoStatus();
    this.iniciarListaOpcaoCategoria();
    this.setMostrarComboCategoria();
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
    this.protocoloServico.getListaProtocolo(this.filtro, pagina).subscribe(paginacao => {
      this.paginacao = paginacao;
      this.spinnerServico.hide();
    });
  }

  exibirListaProtocolo(): boolean {
    return this.paginacao.lista.length > 0;
  }

  verDetalheProtocolo(idProtocolo: number): void {
    this.router.navigate([URLS_NAMES.detalheProtocolo, idProtocolo]);
  }

  alterarBadgeStatus(status: string): string {
    if (status === 'DEFERIDO') {
      return 'badge badge-pill badge-success';
    } else if (status === 'INDEFERIDO') {
      return 'badge badge-pill badge-danger';
    } else if (status === 'ABERTO') {
      return 'badge badge-pill badge-info';
    } else {
      return 'badge badge-pill badge-warning';
    }
  }

  private iniciarFormPesquisaProtocolo(): void {
    this.formPesquisaProtocolo = this.formBuilder.group({
      tipo: this.formBuilder.control(TipoConsultaListaProtocolo.OPCAO_TODOS),
      categoria: this.formBuilder.control(CategoriaProtocoloConsultado.DOCENTE_LOGADO, Validators.required),
      idProtocolo: this.formBuilder.control(''),
      idStatus: this.formBuilder.control('')
    });
  }

  private setMostrarComboCategoria(): void {
    this.spinnerServico.show();
    this.docenteServico.getDadosDocenteLogado().subscribe(docenteLogado => {
      this.mostrarComboCategoria = docenteLogado.perfil == Perfil.PROFESSOR;
      if (!this.mostrarComboCategoria) {
        this.formPesquisaProtocolo.controls['categoria'].setValue(CategoriaProtocoloConsultado.OUTROS_DOCENTES);
      }
      this.spinnerServico.hide();
      this.pesquisarProtocolo();
    });
  }

  private iniciarListaOpcaoTipoConsulta(): void {
    this.listaOpcaoTipoConsulta = [
      { descricao: "Todos", valor: TipoConsultaListaProtocolo.OPCAO_TODOS },
      { descricao: "Código", valor: TipoConsultaListaProtocolo.OPCAO_CODIGO },
      { descricao: "Status", valor: TipoConsultaListaProtocolo.OPCAO_STATUS }
    ];
  }

  private iniciarListaOpcaoCategoria(): void {
    this.listaOpcaoCategoria = [
      { descricao: 'Meus protocolos', valor: CategoriaProtocoloConsultado.DOCENTE_LOGADO },
      { descricao: 'Avaliar', valor: CategoriaProtocoloConsultado.AVALIAR }
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
      Validators.pattern('^[1-9][0-9]*$')
    ]);
    campoIdProtocolo.updateValueAndValidity();
  }

  private adicionarValidacaoConsultaStatus(): void {
    let campoStatus = this.formPesquisaProtocolo.controls.idStatus;
    campoStatus.setValue('');
    campoStatus.setValidators(Validators.required);
    campoStatus.updateValueAndValidity();
  }

  private limparValidacoesCampos(): void {

    let idProtocolo = this.formPesquisaProtocolo.controls.idProtocolo;
    let idStatus = this.formPesquisaProtocolo.controls.idStatus;

    idProtocolo.clearValidators();
    idStatus.clearValidators();

    idProtocolo.updateValueAndValidity();
    idStatus.updateValueAndValidity();
  }
}
