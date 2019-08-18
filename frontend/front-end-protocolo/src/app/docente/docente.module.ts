//core
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

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
    CommonModule
  ]
})
export class DocenteModule { }
