/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
//core
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

//servicos
import { DocenteServico } from 'src/app/docente/docente.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

//constantes
import { URLS_NAMES, Perfil } from 'src/app/app.constante';

//modelos
import { DocenteLogado } from 'src/app/docente/docente.modelo';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  docenteLogado: DocenteLogado;
  private inscricaoEventoRota: Subscription;

  constructor(
    private router: Router,
    private docenteServico: DocenteServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  /**
   * Caso o docente nao esteja logado
   * ele sera redirecionado para a tela de login.
   */
  ngOnInit(): void {

    if (!this.docenteServico.isLogado()) {
      this.router.navigate([URLS_NAMES.login]);
    }

    this.inscricaoEventoRota = this.router.events.subscribe(event => {
      this.spinnerServico.show();
      this.docenteServico.getDadosDocenteLogado().subscribe(docenteLogado => {
        this.docenteLogado = docenteLogado;
        this.spinnerServico.hide();
      });
    });
  }

  mostrarMenu(): boolean {
    return this.docenteServico.isLogado();
  }

  mostrarMenuCoordenadorSecretaria(): boolean {
    if (this.docenteLogado) {
      return this.docenteLogado.perfil == Perfil.ADMIN ||
        this.docenteLogado.perfil == Perfil.COORDENADOR ||
        this.docenteLogado.perfil == Perfil.SECRETARIA;
    }
    return false;
  }

  mostrarMenuProfessor(): boolean {
    if (this.docenteLogado) {
      return this.docenteLogado.perfil == Perfil.PROFESSOR;
    }
    return false;
  }

  deslogar(): void {
    this.docenteServico.deslogar();
  }
}
