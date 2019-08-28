/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */

 //core
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

//terceiros
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

//modelos
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

//utilitarios
import { REGEXS } from 'src/app/app.constante';

//servico
import { UtilServico } from 'src/app/compartilhado/util.servico';
import { DocenteServico } from 'src/app/docente/docente.servico';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  public listaPerfil: Array<Opcao>;
  public form: FormGroup;

  constructor(
    private spinnerServico: Ng4LoadingSpinnerService,
    private docenteServico: DocenteServico,
    private utilServico: UtilServico,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.iniciarForm();
    this.carregarListaPerfil();
  }

  cadastrar(): void {
    this.spinnerServico.show();
    this.docenteServico.cadastrarDocente(this.form.value).subscribe((msg) => {
      this.spinnerServico.hide();
      alert(msg);
    });
  }

  private iniciarForm(): void {
    this.form = this.formBuilder.group({
      email: this.formBuilder.control('',[
        Validators.required,
        Validators.pattern(REGEXS.email)
      ]),
      nome: this.formBuilder.control('', Validators.required),
      idPerfil: this.formBuilder.control('', [Validators.required])
    });
  }

  private carregarListaPerfil(): void {
    this.spinnerServico.show();
    this.utilServico.getListaPerfil().subscribe((listaPerfil: Array<Opcao>) =>  {
      this.listaPerfil = listaPerfil;
      this.spinnerServico.hide();
    });
  } 
}
