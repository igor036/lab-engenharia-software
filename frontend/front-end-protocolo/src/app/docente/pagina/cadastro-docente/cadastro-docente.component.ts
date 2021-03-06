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
import { UtilServico } from 'src/app/compartilhado/servico/util.servico';
import { DocenteServico } from 'src/app/docente/docente.servico';
import { ModalServico } from 'src/app/compartilhado/componentes/modal/modal.servico';

@Component({
  selector: 'app-cadastro-docente',
  templateUrl: './cadastro-docente.component.html',
  styleUrls: ['./cadastro-docente.component.scss']
})
export class CadastroDocenteComponent implements OnInit {

  public listaPerfil: Array<Opcao>;
  public form: FormGroup;

  constructor(
    private spinnerServico: Ng4LoadingSpinnerService,
    private docenteServico: DocenteServico,
    private utilServico: UtilServico,
    private formBuilder: FormBuilder,
    private modalServico: ModalServico
  ) { }

  ngOnInit() {
    this.iniciarForm();
    this.carregarListaPerfil();
  }

  cadastrar(): void {
    this.spinnerServico.show();
    this.docenteServico.cadastrarDocente(this.form.value).subscribe((msg) => {
      this.spinnerServico.hide();
      this.modalServico.exibirSucesso(msg);
    });
  }

  private iniciarForm(): void {
    this.form = this.formBuilder.group({
      email: this.formBuilder.control('', [
        Validators.required,
        Validators.pattern(REGEXS.email)
      ]),
      nome: this.formBuilder.control('', Validators.required),
      idPerfil: this.formBuilder.control('', [Validators.required])
    });
  }

  private carregarListaPerfil(): void {
    this.spinnerServico.show();
    this.utilServico.getListaPerfil().subscribe((listaPerfil: Array<Opcao>) => {
      this.listaPerfil = listaPerfil;
      this.spinnerServico.hide();
    });
  }
}
