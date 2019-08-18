//core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

//terceiros
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';

//paginas
import { LoginComponent } from './pagina/login/login.component';
import { PerfilComponent } from './pagina/perfil/perfil.component';

const COMPONENTES = [
  LoginComponent, 
  PerfilComponent
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
