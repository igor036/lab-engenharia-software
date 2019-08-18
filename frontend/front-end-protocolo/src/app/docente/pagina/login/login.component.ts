/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
//core
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

//terceiros
import { Subscription } from 'rxjs';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

//constantes
import { REGEXS, URLS_NAMES } from 'src/app/app.constante';

//servico
import { DocenteServico } from 'src/app/docente/docente.servico';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public form:  FormGroup;

  constructor (
    private formBuilder: FormBuilder,
    private docenteServico: DocenteServico,
    private spinnerServico: Ng4LoadingSpinnerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.iniciarForm()
  }

  logar(): void {
    this.spinnerServico.show();
    let inscricao: Subscription = this.docenteServico.logar(this.form.value).subscribe((docenteLogado) => {
      this.spinnerServico.hide();
      this.docenteServico.setDocenteLogado(docenteLogado);
      inscricao.unsubscribe();
      this.router.navigate([URLS_NAMES.home]);
    });
  }
  
  private iniciarForm(): void {
    this.form = this.formBuilder.group({
      email: this.formBuilder.control('', [
        Validators.required,
        Validators.pattern(REGEXS.email)
      ]),
      senha: this.formBuilder.control('', Validators.required)
    });
  }
}
