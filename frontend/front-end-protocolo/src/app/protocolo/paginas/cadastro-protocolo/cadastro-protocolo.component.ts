import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';
import { UtilServico } from 'src/app/compartilhado/servico/util.servico';
import { PedidoProtocoloVisualizar, CadastrarProtocolo } from 'src/app/protocolo/protocolo.modelo';
import { DocenteServico } from 'src/app/docente/docente.servico';
import { DocenteLogado } from 'src/app/docente/docente.modelo';
import { ModalServico } from 'src/app/compartilhado/componentes/modal/modal.servico';
import { Router } from '@angular/router';

//Constante
import { URLS_NAMES } from 'src/app/app.constante';
import * as moment from "moment";

@Component({
  selector: 'app-cadastro-protocolo',
  templateUrl: './cadastro-protocolo.component.html',
  styleUrls: ['./cadastro-protocolo.component.scss']
})
export class CadastroProtocoloComponent implements OnInit {

  public docenteLogado: DocenteLogado;
  public formProtocolo: FormGroup;
  public formPedido: FormGroup;
  public listaEspecie: Array<Opcao> = [];
  public listaBioterio: Array<Opcao> = [];
  public listaPedido: Array<PedidoProtocoloVisualizar> = [];

  constructor(
    private formBuilder: FormBuilder,
    private utilServico: UtilServico,
    private docenteServico: DocenteServico,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService,
    private modalServico: ModalServico,
    private router: Router
  ) { }

  ngOnInit() {
    this.carregarDocente();
    this.carregarListas();
    this.iniciarForms();
  }

  cadastrar(): void {
    this.spinnerServico.show();
    this.protocoloServico.cadastrarProtocolo(this.getCadastrarProtocolo()).subscribe(resposta => {
      this.spinnerServico.hide();
      this.formProtocolo.reset();
      this.modalServico.exibirSucesso(resposta);
      this.listaPedido = [];
      this.router.navigate([URLS_NAMES.consultaProtocolo]);
    });
  }

  adicionarPedido(): void {

    let idEspecie: number = this.formPedido.controls['especie'].value;
    let quantidade: number = this.formPedido.controls['quantidade'].value;
    let idBioterio: number = this.formPedido.controls['bioterio'].value;

    let opcaoEspecie: Opcao = this.listaEspecie.find(especie => especie.valor == idEspecie);
    let opcaoBioterio: Opcao = this.listaBioterio.find(bioterio => bioterio.valor == idBioterio);

    let pedido: PedidoProtocoloVisualizar = {
      especie: opcaoEspecie,
      bioterio: opcaoBioterio,
      quantidade: quantidade
    };

    this.listaPedido.push(pedido);

    this.formPedido.controls['especie'].setValue('');
    this.formPedido.controls['quantidade'].reset();
    this.formPedido.controls['bioterio'].setValue('');
  }

  removerPedido(pedidoRemover: PedidoProtocoloVisualizar): void {
    this.listaPedido = this.listaPedido.filter(pedido => pedidoRemover != pedido);
  }

  exibirListaPedido(): boolean {
    return this.listaPedido.length > 0;
  }

  exibirMensagemPeriodoInvalido(): boolean {

    let datInicioValida: boolean = this.formProtocolo.controls['dataInicio'].valid;
    let dataFimValida: boolean = this.formProtocolo.controls['dataFim'].valid;
    let dataInicio: Date = this.formProtocolo.controls['dataInicio'].value as Date;
    let dataFim: Date = this.formProtocolo.controls['dataFim'].value as Date;

    return datInicioValida && dataFimValida && dataInicio > dataFim;
  }

  exibirMensagemDataInicioInvalida(): boolean {
    let dataInicial = this.formProtocolo.value.dataInicio;
    let dataAtual = moment().toDate();

    if (dataInicial) {
      return dataInicial < dataAtual;
    }
  }

  exibirMensagemDataFimInvalida(): boolean {
    let dataAtual = moment().toDate();
    let dataFim = this.formProtocolo.value.dataFim;

    if (dataFim) {
      return dataFim < dataAtual;
    }
  }

  liberarBotaoCriarNovoProtocolo(): boolean {
    return this.formProtocolo.valid &&
      this.listaPedido.length > 0 &&
      !this.exibirMensagemPeriodoInvalido();
  }

  private iniciarForms(): void {
    this.formProtocolo = this.formBuilder.group({
      justificativa: this.formBuilder.control('', Validators.required),
      resumoPt: this.formBuilder.control('', Validators.required),
      resumoEn: this.formBuilder.control('', Validators.required),
      dataInicio: this.formBuilder.control('', Validators.required),
      dataFim: this.formBuilder.control('', Validators.required)
    });

    this.formPedido = this.formBuilder.group({
      especie: this.formBuilder.control('', Validators.required),
      quantidade: this.formBuilder.control('', [
        Validators.required,
        Validators.pattern('^[1-9][0-9]*$')
      ]),
      bioterio: this.formBuilder.control('', Validators.required)
    });
  }

  private carregarListas(): void {

    let qtdListasCarregadas: number = 0;
    let funcaoEsconderSpinner: Function = () => {
      if (++qtdListasCarregadas == 2) {
        this.spinnerServico.hide();
      }
    };

    this.spinnerServico.show();
    this.utilServico.getListaEspecie().subscribe(listaEspecie => {
      this.listaEspecie = listaEspecie;
      funcaoEsconderSpinner();
    });

    this.utilServico.getListaBioterio().subscribe(listaBioterio => {
      this.listaBioterio = listaBioterio;
      funcaoEsconderSpinner();
    });
  }

  private carregarDocente(): void {
    this.docenteServico.getDadosDocenteLogado().subscribe(docenteLogado => {
      this.docenteLogado = docenteLogado;
    });
  }

  private getCadastrarProtocolo(): CadastrarProtocolo {
    let cadastro: CadastrarProtocolo = this.formProtocolo.value;
    cadastro.litaPedidoProtocolo = this.listaPedido.map(pedido => {
      return {
        idEspecie: pedido.especie.valor,
        quantidade: pedido.quantidade,
        idBioterio: pedido.bioterio.valor
      };
    });
    return cadastro;
  }
}