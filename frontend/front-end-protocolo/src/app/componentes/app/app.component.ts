//core
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

//servicos
import { DocenteServico } from 'src/app/docente/docente.servico';

//constantes
import { URLS_NAMES } from 'src/app/app.constante';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
    private docenteServico: DocenteServico,
    private router: Router
  ) {}
    
  ngOnInit(): void {
    let rota = this.docenteServico.isLogado() ? URLS_NAMES.home : URLS_NAMES.login;
    this.router.navigate([rota]);
  }
}
