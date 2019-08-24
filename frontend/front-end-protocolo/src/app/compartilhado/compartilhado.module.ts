import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//componentes
import { InputComponent } from './componentes/input/input.component';

const COMPONENTES = [
  InputComponent
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
