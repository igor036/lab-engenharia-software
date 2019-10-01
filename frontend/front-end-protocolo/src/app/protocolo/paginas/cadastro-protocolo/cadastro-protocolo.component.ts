import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ProtocoloServico } from '../../protocolo.servico';

@Component({
  selector: 'app-cadastro-protocolo',
  templateUrl: './cadastro-protocolo.component.html',
  styleUrls: ['./cadastro-protocolo.component.scss']
})
export class CadastroProtocoloComponent implements OnInit {

  public form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private protocoloServico: ProtocoloServico
  ) { }

  ngOnInit() {
    this.iniciarForm();
  }

  cadastrar(): void  {

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
