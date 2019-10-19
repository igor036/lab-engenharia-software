import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ProtocoloServico } from 'src/app/protocolo/protocolo.servico';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { Opcao } from 'src/app/compartilhado/compartilhado.modelo';

@Component({
  selector: 'app-cadastro-protocolo',
  templateUrl: './cadastro-protocolo.component.html',
  styleUrls: ['./cadastro-protocolo.component.scss']
})
export class CadastroProtocoloComponent implements OnInit {

  public form: FormGroup;
  public listaEspecie: Array<Opcao> = [];
  public listaBioterio: Array<Opcao> = [];

  constructor(
    private formBuilder: FormBuilder,
    private protocoloServico: ProtocoloServico,
    private spinnerServico: Ng4LoadingSpinnerService
  ) { }

  ngOnInit() {
    this.carregarListas();
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
      especie: this.formBuilder.control('', Validators.required),
      quantidade: this.formBuilder.control('', Validators.required),
      bioterio: this.formBuilder.control('', Validators.required)
    });
  }

  private carregarListas(): void {
    
    let qtdListasCarregadas: number = 0;
    let funcaoEsconderSpinner: Function = () =>  {
      if (++qtdListasCarregadas == 2) {
        this.spinnerServico.hide();
      }
    };

    this.spinnerServico.show();
    this.protocoloServico.getListaEspecie().subscribe(listaEspecie => {
      this.listaEspecie = listaEspecie;
      funcaoEsconderSpinner();
    });

    this.protocoloServico.getListaBioterio().subscribe(listaBioterio => {
      this.listaBioterio = listaBioterio;
      funcaoEsconderSpinner();
    });
  }
}
