/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 18/08/2019
 */
//core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//terceiros
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

//paginas
import { LoginComponent } from './pagina/login/login.component';
import { PerfilComponent } from './pagina/perfil/perfil.component';
import { CadastroComponent } from './pagina/cadastro/cadastro.component';

const COMPONENTES = [
  LoginComponent, 
  PerfilComponent,
  CadastroComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule
  ]
})
export class DocenteModule { }
