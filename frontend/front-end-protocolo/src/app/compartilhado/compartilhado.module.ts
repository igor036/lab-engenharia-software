/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 24/08/2019
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//componentes
import { BotaoComponent } from './componentes/botao/botao.component';
import { InputTextoComponent } from './componentes/input-texto/input-texto.component';
import { SelecaoItemComponent } from './componentes/selecao-item/selecao-item.component';
import { InputDataComponent } from './componentes/input-data/input-data.component';
import { InputAreaTextoComponent } from './componentes/input-area-texto/input-area-texto.component';
import { PaginacaoComponent } from './componentes/paginacao/paginacao.component';
import { ModalMensagemComponent } from './componentes/modal/modal-mensagem/modal-mensagem.component';
import { InputAutoCompleteComponent } from './componentes/input-auto-complete/input-auto-complete.component';

//servicos
import { UtilServico } from './servico/util.servico';
import { ModalServico } from './componentes/modal/modal.servico';

//modulos
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker'
import { PaginationModule } from 'ngx-bootstrap';

const COMPONENTES = [
  BotaoComponent,
  InputTextoComponent,
  SelecaoItemComponent,
  InputDataComponent,
  InputAreaTextoComponent,
  PaginacaoComponent,
  ModalMensagemComponent,
  InputAutoCompleteComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  entryComponents: [
    ModalMensagemComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule,
    PaginationModule
  ],
  providers: [
    UtilServico,
    ModalServico
  ]
})
export class CompartilhadoModule { }
