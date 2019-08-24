import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//componentes
import { BotaoComponent } from './componentes/botao/botao.component';
import { InputTextoComponent } from './componentes/input-texto/input-texto.component';
import { SelecaoItemComponent } from './componentes/selecao-item/selecao-item.component';

//servicos
import { UtilServico } from './util.servico';

const COMPONENTES = [
  BotaoComponent,
  InputTextoComponent,
  SelecaoItemComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    UtilServico
  ]
})
export class CompartilhadoModule { }
