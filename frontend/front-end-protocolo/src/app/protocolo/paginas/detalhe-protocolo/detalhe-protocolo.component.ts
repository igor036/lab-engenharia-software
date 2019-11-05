import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

//modelo
import { DetalheProtocolo, AtribuirParecerista, Parecerista, AvaliarProtocolo } from 'src/app/protocolo/protocolo.modelo';
import { DocenteLogado } from 'src/app/docente/docente.modelo';

//servico
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { DocenteServico } from 'src/app/docente/docente.servico';


//Constante
import { Perfil } from 'src/app/app.constante';
import { ModalServico } from 'src/app/compartilhado/componentes/modal/modal.servico';
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';
import { URLS_NAMES } from 'src/app/app.constante';



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
  public formAtribuirParecerista: FormGroup;
  public formObservacaoParecer: FormGroup;
  public listaSugestoesPareceristas: Array<Opcao>;
  public pareceristaEscolhido: Parecerista;


  constructor(
    private rota: ActivatedRoute,
    private docenteServico: DocenteServico,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService,
    private formBuilder: FormBuilder,
    private modalServico: ModalServico,
    private router: Router
  ) {
    this.ID_PROTOCOLO = this.rota.snapshot.params['idProtocolo'];
  }

  ngOnInit() {
    this.listaSugestoesPareceristas = [];
    this.carregarDetalhesProtocoloEDocenteLogado();
    this.iniciarFormAtribuirParecerista();
    this.iniciarFormObservacaoParecer();
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

  exibirCampoAvaliar(): boolean {
    if (this.detalheProtocolo && this.docenteLogado) {
      console.log('status: ', this.detalheProtocolo.permitido);
      return this.detalheProtocolo.matriculaDocente != this.docenteLogado.matricula && this.docenteLogado.perfil === Perfil.PROFESSOR && this.detalheProtocolo.permitido === null;

    }
    return false;
  }

  exibirAdicionarParecerista(): boolean {
    let perfilDocente = this.docenteLogado.perfil
    return perfilDocente === Perfil.COORDENADOR || perfilDocente === Perfil.SECRETARIA;
  }

  exibirDadosParecerista(): boolean {
    return this.pareceristaEscolhido != undefined;
  }


  iniciarFormAtribuirParecerista(): void {
    this.formAtribuirParecerista = this.formBuilder.group({
      descricao: this.formBuilder.control('',
        [Validators.required])
    });
  }

  iniciarFormObservacaoParecer(): void {
    this.formObservacaoParecer = this.formBuilder.group({
      descricao: this.formBuilder.control('', Validators.required)
    });
  }

  atribuirParecerista(): void {
    let dados: AtribuirParecerista = {
      idAvaliador: this.pareceristaEscolhido.valor,
      idProtocolo: this.detalheProtocolo.id
    };

    this.spinnerServico.show();
    this.protocoloServico.atribuirParecerista(dados).subscribe(msg => {
      this.modalServico.exibirSucesso(msg);
      this.router.navigate([URLS_NAMES.consultaProtocolo]);
      this.spinnerServico.hide();
    });
  }

  avaliarProtocolo(valor: boolean): void {
    let dados: AvaliarProtocolo = {
      deferido: valor,
      descricao: this.formObservacaoParecer.value.descricao,
      idProtocolo: this.detalheProtocolo.id
    };

    let avaliacao = valor === true ? 'deferir' : 'indeferir';
    let mensagem: string = "Confirma a avaliação " + avaliacao + "?"

    this.spinnerServico.show();
    this.protocoloServico.avaliarProtocolo(dados).subscribe(msg => {
      this.modalServico.exibirSucesso(msg);
      this.spinnerServico.hide();
      this.router.navigate([URLS_NAMES.consultaProtocolo]);
    });
  }

  liberarBotaoAtribuirParecerista(): boolean {
    return this.formAtribuirParecerista.valid;
  }

  liberarBotoesAvaliar(): boolean {
    return this.formObservacaoParecer.valid;
  }

  atualizarListaDeSugestaoPareceristas(descricao: string): void {
    if (this.formAtribuirParecerista.value.descricao) {
      this.docenteServico.getListaSugestaoDocente(descricao).subscribe(lista => {
        this.listaSugestoesPareceristas = lista;
      });
    } else {
      this.listaSugestoesPareceristas = [];
    }
  }

  getPareceristaEscolhido(valor): void {
    this.pareceristaEscolhido = valor;
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
