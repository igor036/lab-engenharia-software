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

//modulo de componentes compartilhados
import { CompartilhadoModule } from 'src/app/compartilhado/compartilhado.module';

//paginas
import { LoginComponent } from './pagina/login/login.component';
import { PerfilComponent } from './pagina/perfil/perfil.component';
import { CadastroDocenteComponent } from './pagina/cadastro-docente/cadastro-docente.component';

const COMPONENTES = [
  LoginComponent, 
  PerfilComponent,
  CadastroDocenteComponent
];

@NgModule({
  declarations: COMPONENTES,
  exports: COMPONENTES,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule,
    CompartilhadoModule
  ]
})
export class DocenteModule { }
