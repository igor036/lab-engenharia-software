import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ProtocoloServico } from '../../protocolo.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';

@Component({
  selector: 'app-cadastro-protocolo',
  templateUrl: './cadastro-protocolo.component.html',
  styleUrls: ['./cadastro-protocolo.component.scss']
})
export class CadastroProtocoloComponent implements OnInit {

  public form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.iniciarForm();
  }

  cadastrar(): void  {
    this.spinnerServico.show();
    this.protocoloServico.cadastrarProtocolo(this.form.value).subscribe(resposta =>  {
      this.spinnerServico.hide();
      alert(resposta)
    });
  }

  private iniciarForm(): void {
    this.form = this.formBuilder.group({
      justificativa: this.formBuilder.control('', Validators.required),
      resumoPt: this.formBuilder.control('', Validators.required),
      resumoEn: this.formBuilder.control('', Validators.required),
      dataInicio: this.formBuilder.control('', Validators.required),
      dataFim: this.formBuilder.control('', Validators.required),
    });
  }
}
