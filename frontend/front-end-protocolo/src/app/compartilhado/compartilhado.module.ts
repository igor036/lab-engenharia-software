import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//componentes
import { InputTextoComponent } from './componentes/input-texto/input-texto.component';
import { BotaoComponent } from './componentes/botao/botao.component';

const COMPONENTES = [
  InputTextoComponent,
  BotaoComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class CompartilhadoModule { }
