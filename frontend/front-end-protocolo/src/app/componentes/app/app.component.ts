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
import { DocenteLogado } from 'src/app/docente/docente.modelo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  private docenteLogado: DocenteLogado;

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
    this.carregarDocenteLogado();
  }

  mostrarMenu(): boolean {
    return this.docenteServico.isLogado();
  }

  deslogar(): void {
    this.docenteServico.deslogar();
  }

  exibirMenuCadastroDocente(): boolean {
    return this.docenteLogado.perfil == Perfil.COORDENADOR ||
      this.docenteLogado.perfil == Perfil.SECRETARIA ||
      this.docenteLogado.perfil == Perfil.ADMIN;
  }

  carregarDocenteLogado(): void {
    this.spinnerServico.show();
    this.docenteServico.getDadosDocenteLogado().subscribe(docenteLogado => {
      this.docenteLogado = docenteLogado;
      this.spinnerServico.hide();
    });
  }
}
