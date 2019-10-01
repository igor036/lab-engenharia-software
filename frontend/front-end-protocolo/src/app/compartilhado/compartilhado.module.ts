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

//servicos
import { UtilServico } from './util.servico';

//modulos
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

const COMPONENTES = [
  BotaoComponent,
  InputTextoComponent,
  SelecaoItemComponent,
  InputDataComponent,
  InputAreaTextoComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot()
  ],
  providers: [
    UtilServico
  ]
})
export class CompartilhadoModule { }
